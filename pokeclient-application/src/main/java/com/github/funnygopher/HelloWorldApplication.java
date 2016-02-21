package com.github.funnygopher;

import com.github.funnygopher.health.TemplateHealthCheck;
import com.github.funnygopher.resource.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing yet
    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(helloWorldConfiguration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        final HelloWorldResource resource = new HelloWorldResource(
                helloWorldConfiguration.getTemplate(),
                helloWorldConfiguration.getDefaultName()
        );
        environment.jersey().register(resource);
    }
}
