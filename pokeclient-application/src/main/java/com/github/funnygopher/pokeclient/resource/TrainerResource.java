package com.github.funnygopher.pokeclient.resource;

import com.codahale.metrics.annotation.Timed;
import com.github.funnygopher.pokeclient.api.Saying;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/trainer")
@Produces(MediaType.APPLICATION_JSON)
public class TrainerResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public TrainerResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
