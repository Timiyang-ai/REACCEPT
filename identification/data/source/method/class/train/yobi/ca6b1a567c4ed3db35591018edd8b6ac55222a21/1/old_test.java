    @Test
    public void getHostname() {
        FakeApplication app;
        Map<String, String> additionalConfiguration = support.Helpers.makeTestConfig();

        additionalConfiguration.put("application.hostname", "test.yobi.com");
        additionalConfiguration.put("application.port", "8080");
        app = support.Helpers.makeTestApplication(additionalConfiguration);
        Helpers.start(app);
        assertThat(Config.getHostport("localhost")).isEqualTo("test.yobi.com:8080");
        Helpers.stop(app);

        additionalConfiguration.put("application.hostname", "test.yobi.com");
        additionalConfiguration.put("application.port", null);
        app = support.Helpers.makeTestApplication(additionalConfiguration);
        Helpers.start(app);
        assertThat(Config.getHostport("localhost:9000")).isEqualTo("test.yobi.com");
        Helpers.stop(app);

        additionalConfiguration.put("application.hostname", null);
        additionalConfiguration.put("application.port", "8080");
        app = support.Helpers.makeTestApplication(additionalConfiguration);
        Helpers.start(app);
        assertThat(Config.getHostport("localhost:9000")).isEqualTo("localhost:9000");
        Helpers.stop(app);

        additionalConfiguration.put("application.hostname", null);
        additionalConfiguration.put("application.port", null);
        app = support.Helpers.makeTestApplication(additionalConfiguration);
        Helpers.start(app);
        assertThat(Config.getHostport("localhost:9000")).isEqualTo("localhost:9000");
        Helpers.stop(app);
    }