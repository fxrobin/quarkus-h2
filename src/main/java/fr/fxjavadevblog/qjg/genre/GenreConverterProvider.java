package fr.fxjavadevblog.qjg.genre;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.fxjavadevblog.qjg.utils.GenericEnumConverter;

/**
 * JAX-RS provider for Genre conversion. This converter is registered because of
 * the Prodiver annotation.
 * 
 * @author François-Xavier Robin
 */

@Provider
public class GenreConverterProvider implements ParamConverterProvider
{
    private final Logger log = LoggerFactory.getLogger(GenreConverterProvider.class);
    private final ParamConverter<Genre> converter = GenericEnumConverter.of(Genre.class);

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations)
    {
        log.debug("Registering GenreConverter");
        return (Genre.class.equals(rawType)) ? (ParamConverter<T>) converter : null;
    }
}
