package fr.fxjavadevblog.qjg.utils;

import java.util.EnumSet;
import java.util.Optional;

import javax.ws.rs.ext.ParamConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Generic JAX-RS Enum converter based on the jackson JsonProperty annotation to
 * get the mapping.
 * 
 * @author François-Xavier Robin
 *
 * @param <T>
 */

public class GenericEnumConverter<T extends Enum<T>> implements ParamConverter<T>
{
    private static final Logger log = LoggerFactory.getLogger(GenericEnumConverter.class);
    
    /**
     * bi-directionnal Map to store enum value as key and its string representation as value.
     * The string representation is retrieved through the JsonProperty annotation put on the enum constant. 
     */
    private final BiMap<T, String> biMap =  HashBiMap.create();

    /**
     * returns a Generic converter for an enum class in the context of JAX-RS ParamConverter.
     * 
     * @param <T>
     *    enum type
     * @param t
     *    enum type class
     * @return
     *    a generic converter used by JAX-RS.
     */
    public static <T extends Enum<T>> ParamConverter<T> of(Class<T> t)
    {
        return new GenericEnumConverter<>(t);
    }
    
    private GenericEnumConverter(Class<T> t)
    {
        log.debug("Generating conversion map for enum {}", t);
        EnumSet.allOf(t).forEach(v -> {
            try
            {
                String enumValue = v.name();
                JsonProperty annotation =  v.getClass().getDeclaredField(enumValue).getAnnotation(JsonProperty.class);
                // get the annotation if exists or take the classic enum representation
                String result = Optional.ofNullable(annotation).map(JsonProperty::value).orElse(enumValue);
                log.debug("Enum value {}.{} is mapped to \"{}\"", t.getSimpleName(), v.name(), result);
                biMap.put(v, result);
            }
            catch (NoSuchFieldException | SecurityException e)
            {
                log.error("Error while populating BiMap of enum {}", t.getClass());
                log.error("Thrown by ", e);
            }
        });
    }

    /**
     * returns the enum type constant from this string representation.
     */
    @Override
    public T fromString(String value)
    {	
        T returnedValue = biMap.inverse().get(value); 
        log.debug("Converting String \"{}\" to {}.{}", value, returnedValue.getClass(), returnedValue);
        return returnedValue;
    }
    
    
    /**
     * returns the string represenation from this enum type constant.
     */
    @Override
    public String toString(T t)
    {
        String returnedValue = biMap.get(t);
        log.debug("Converting Enum {}.{} to String \"{}\"", t.getClass(), t, returnedValue);
        return returnedValue;
    }
}
