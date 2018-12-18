package com.tinklabs.b2c.cloud.parameterservice.aop;

import com.tinklabs.b2c.cloud.parameterservice.component.CacheComponent;
import com.tinklabs.b2c.cloud.parameterservice.component.CacheService;
import com.tinklabs.b2c.cloud.parameterservice.consts.Consts;
import com.tinklabs.b2c.cloud.parameterservice.exceptions.AwfException;
import com.tinklabs.b2c.cloud.parameterservice.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
@Order(2)
public class QueryCacheAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryCacheAspect.class);

    private static String expireTimeStr = "21600";
    private static int CACHE_DATA_EXPIRE_SECONDS = 60*60*6;
    static{
        try{
            CACHE_DATA_EXPIRE_SECONDS = Integer.parseInt(expireTimeStr);
        } catch (Exception e) {
            LOGGER.warn("CACHE_DATA_EXPIRE_SECONDS will set default expire time 6 hours due to failed to parse string <"+expireTimeStr+"> to Integer.", e);
        }
    }

    @Autowired
    @Qualifier("cacheComponent")
    private CacheComponent cache;

    @Pointcut("execution(* com.tinklabs.b2c.cloud.parameterservice.service..*.*(..)) && @annotation(com.tinklabs.b2c.cloud.parameterservice.annotation.QueryCache)")
    public void normalQueryCacheServiceMethod(){}

    @Around("normalQueryCacheServiceMethod()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Object rvt = null;

        Object[] args = pjp.getArgs();
        Signature sig = pjp.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        try{
            String key = createKey(args,sig,msig);
            //if froceCache(args,sig,msig) == true,则表示当前需要从数据库取 而不从cache取,并刷新cache
            if(froceCache(args,sig,msig)){
                rvt =  pjp.proceed();
                saveOrUpdateCache(key, rvt);
                //LOGGER.info("refresh the cache : " + key);
                return rvt;
            }

            Object target = pjp.getTarget();
            Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            Class tClass = currentMethod.getReturnType();
            //LOGGER.info("return object is : " + tClass.getName());
            rvt = cache.get(key,tClass);
            //if not get cache, re-set it
            if(null == rvt){
                rvt =  pjp.proceed();
                saveOrUpdateCache(key,rvt);
            }else{
                //LOGGER.info("get the result from cache : " + JsonUtils.toJson(rvt));
            }
        }catch (Exception e){
            rvt =  pjp.proceed();
            //LOGGER.error("run on cache aspect exception: " + e.getMessage());
        }

        return rvt;
    }

    private void saveOrUpdateCache(String key,Object rvt){
        try{
//            cache.set(key, rvt, CACHE_DATA_EXPIRE_SECONDS);
            cache.set(key, rvt);
            //LOGGER.info("set the result into cache success: " + JsonUtils.toJson(rvt));
        }catch (Exception e){
            LOGGER.error("set the result into cache failed: " + e.getMessage());
        }
    }

    private boolean froceCache(Object[] args, Signature sig, MethodSignature msig) throws AwfException{
        Parameter[] parameters =  msig.getMethod().getParameters();

        String forceFlag = Consts.FALSE;
        if(null != args && args.length > 0){
            try{
                for(int i=0; i< args.length; i++){
                    //如果有一个forceData的标识,则标识当前需要强制刷新缓存
                   if(args[i].toString().equals("forceData")){
                       forceFlag = Consts.TRUE;
                       break;
                   }
                }
            }catch (Exception e){
                LOGGER.error("catch the force para exception:: " + e.getMessage());
            }
        }

        //LOGGER.info("The force flag is ["+forceFlag+"]");
        return StringUtils.isTrue(forceFlag);
    }


    private String createKey(Object[] args, Signature sig, MethodSignature msig) throws AwfException {
        StringBuffer params = new StringBuffer();

        if(null != args && args.length > 0){
            try{
                for(int i=0; i< args.length;i++ ){
                    //如果有一个forceData的标识
                    if(!args[i].toString().equals("forceData") && !args[i].toString().equals("notForceData")){
                        params.append((args[i]==null) ? "null":args[i].toString());
                    }
                    if(i < args.length - 1){
                        params.append(",");
                    }
                }
            } catch (Exception e) {
                LOGGER.error("paras append error: " + e.getMessage());
            }
        }else{
            //LOGGER.info("paras is null : ");
        }



        String key = sig.getDeclaringTypeName()+"."+sig.getName()+"#"+params.toString()+"";
        key = key.trim().replaceAll("[\\s]", "_");

        key = key.trim().replace(".", "_");

        key = key.trim().replace("?", "_");

        key = key.trim().replace(",", "_");

        key = key.trim().replace("#", "_");

        key = key.trim().replace("$", "_");

        key = key.trim().replace("=", "_");

        key = key.trim().replace(":", "_");

        LOGGER.info("The cache key is ["+key+"]");
        return key;
    }
}
