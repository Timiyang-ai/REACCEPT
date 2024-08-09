    private void addTestFixtures(final String name, final ConfigurationBuilder<BuiltConfiguration> builder) {
        builder.setConfigurationName(name);
        builder.setStatusLevel(Level.ERROR);
        builder.setShutdownTimeout(5000, TimeUnit.MILLISECONDS);
        builder.add(builder.newScriptFile("target/test-classes/scripts/filter.groovy").addIsWatched(true));
        builder.add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
                .addAttribute("level", Level.DEBUG));

        final AppenderComponentBuilder appenderBuilder = builder.newAppender("Stdout", "CONSOLE").addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(builder.newLayout("PatternLayout").
                addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
        appenderBuilder.add(builder.newFilter("MarkerFilter", Filter.Result.DENY,
                Filter.Result.NEUTRAL).addAttribute("marker", "FLOW"));
        builder.add(appenderBuilder);

        builder.add(builder.newLogger("org.apache.logging.log4j", Level.DEBUG, true).
                    add(builder.newAppenderRef("Stdout")).
                    addAttribute("additivity", false));
        builder.add(builder.newLogger("org.apache.logging.log4j.core").
                    add(builder.newAppenderRef("Stdout")));
        builder.add(builder.newRootLogger(Level.ERROR).add(builder.newAppenderRef("Stdout")));

        builder.addProperty("MyKey", "MyValue");
        builder.add(builder.newCustomLevel("Panic", 17));
        builder.setPackages("foo,bar");
    }