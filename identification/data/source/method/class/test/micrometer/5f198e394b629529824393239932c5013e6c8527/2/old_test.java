    @Test
    void writeMeter() {
        Timer timer = Timer.builder("myTimer").register(registry);
        assertThat(registry.writeMeter(timer))
                .contains("{ \"index\" : {} }\n{\"@timestamp\":\"1970-01-01T00:00:00.001Z\",\"name\":\"myTimer\",\"type\":\"timer\",\"count\":\"0.0\",\"total\":\"0.0\",\"max\":\"0.0\"}");
    }