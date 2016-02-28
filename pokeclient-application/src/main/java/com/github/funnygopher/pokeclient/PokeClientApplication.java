package com.github.funnygopher.pokeclient;

import com.github.funnygopher.pokeclient.health.TemplateHealthCheck;
import com.github.funnygopher.pokeclient.resource.TrainerResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PokeClientApplication extends Application<PokeClientConfiguration> {
    public static void main(String[] args) throws Exception {
        new PokeClientApplication().run(args);
    }

    @Override
    public String getName() {
        return "poke-client";
    }

    @Override
    public void initialize(Bootstrap<PokeClientConfiguration> bootstrap) {
        // nothing yet
    }

    @Override
    public void run(PokeClientConfiguration config, Environment environment) throws Exception {
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(config.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        final TrainerResource resource = new TrainerResource(
                config.getTemplate(),
                config.getDefaultName()
        );
        environment.jersey().register(resource);
    }
}
