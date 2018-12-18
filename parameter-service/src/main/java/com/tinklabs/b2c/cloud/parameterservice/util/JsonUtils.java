package com.tinklabs.b2c.cloud.parameterservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * Convert a json string to Object.
     *
     * @author Alva Xie
     *
     * @param content
     *            The json string.
     * @param clazz
     *            The return Object class.
     *
     * @return A instance of class.
     *
     * @throws IOException
     *             Thrown if any I/O error occurred
     */
    public static <T> T toBean(String content, Class<T> clazz)
            throws IOException {
        checkArgument(StringUtils.isNotBlank(content),
                "The parameter content is required.");

        Objects.requireNonNull(clazz, "The parameter clzss is required.");

        return MAPPER.readValue(content, clazz);
    }

    /**
     * Convert a json string to Object.
     *
     * @author Alva Xie
     *
     * @param content
     *            The json string.
     * @param typeReference
     *            The typeReference.
     *
     * @return The converted object.
     *
     * @throws IOException
     *             Thrown if any I/O error occurred
     */
    public static <T> T toBean(String content, TypeReference<T> typeReference)
            throws IOException {
        Objects.requireNonNull(content, "The parameter content is required.");
        Objects.requireNonNull(typeReference,
                "The parameter typeReference is required.");

        return MAPPER.readValue(content, typeReference);
    }

    /**
     * Convert a Object to json string.
     *
     * @author Alva Xie
     *
     * @param obj
     *            The object.
     *
     * @return A json string.
     *
     * @throws JsonProcessingException
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        Objects.requireNonNull(obj, "The parameter obj is required.");

        return MAPPER.writeValueAsString(obj);
    }
}
