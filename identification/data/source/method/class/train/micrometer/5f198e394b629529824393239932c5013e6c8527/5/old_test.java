    @Test
    void writeTimer() {
        Timer timer = Timer.builder("myTimer").register(registry);
        assertThat(registry.writeTimer(timer)).contains("{ \"index\" : {} }\n{\"@timestamp\":\"1970-01-01T00:00:00.001Z\",\"name\":\"myTimer\",\"type\":\"timer\",\"count\":0,\"sum\":0.0,\"mean\":0.0,\"max\":0.0}");
    }