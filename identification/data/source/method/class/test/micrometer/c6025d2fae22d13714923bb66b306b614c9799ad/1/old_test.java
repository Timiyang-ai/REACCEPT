    @Test
    void writeFunctionCounter() {
        FunctionCounter counter = FunctionCounter.builder("myCounter", 123.0, Number::doubleValue).register(registry);
        clock.add(config.step());
        assertThat(registry.writeFunctionCounter(counter))
                .contains("{ \"index\" : {} }\n{\"@timestamp\":\"1970-01-01T00:01:00.001Z\",\"name\":\"myCounter\",\"type\":\"counter\",\"count\":123.0}");
    }