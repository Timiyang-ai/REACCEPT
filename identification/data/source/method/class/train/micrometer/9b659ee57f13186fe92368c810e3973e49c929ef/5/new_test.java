    @Test
    void writeCounter() {
        Counter counter = Counter.builder("myCounter").register(registry);
        counter.increment();
        clock.add(config.step());
        assertThat(registry.writeCounter(counter))
                .contains("{ \"index\" : {} }\n{\"@timestamp\":\"1970-01-01T00:01:00.001Z\",\"name\":\"myCounter\",\"type\":\"counter\",\"count\":1.0}");
    }