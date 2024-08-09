    @Test
    void writeLongTaskTimer() {
        LongTaskTimer timer = LongTaskTimer.builder("longTaskTimer").register(registry);
        assertThat(registry.writeLongTaskTimer(timer))
                .contains("{ \"index\" : {} }\n{\"@timestamp\":\"1970-01-01T00:00:00.001Z\",\"name\":\"longTaskTimer\",\"type\":\"long_task_timer\",\"activeTasks\":0,\"duration\":0.0}");
    }