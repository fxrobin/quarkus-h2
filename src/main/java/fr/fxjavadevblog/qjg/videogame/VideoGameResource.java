package fr.fxjavadevblog.qjg.videogame;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;

import lombok.extern.slf4j.Slf4j;

/**
 * JAX-RS endpoint for Video Games.
 * 
 * @author François-Xavier Robin
 *
 */
@Path("/api/v1/video-games")
@Slf4j
public class VideoGameResource
{
    
    @Inject
    VideoGameRepository videoGameRepository;

    @GET
    @Produces({"application/json", "application/yaml"})
    @Operation(summary = "Get games", description = "Get all video games on Atari ST")
    @Timed(name = "videogames-find-all", absolute = true, description = "A measure of how long it takes to fetch all video games.", unit = MetricUnits.MILLISECONDS)
    public Iterable<VideoGame> findAll()
    {
    	log.info("finAll video-games");
        return videoGameRepository.listAll();
    }
    
    

}
