    @Test
    void addLongTaskTimer() {
        LongTaskTimer longTaskTimer = LongTaskTimer.builder("my.long.task.timer").register(this.registry);
        assertThat(this.registry.addLongTaskTimer(longTaskTimer)).hasSize(2);
    }