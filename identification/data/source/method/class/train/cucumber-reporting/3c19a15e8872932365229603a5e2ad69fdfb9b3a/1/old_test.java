    @Test
    public void size_ReturnsAllStatusCounter() {

        // given
        StatusCounter statusCounter = new StatusCounter();

        // when
        statusCounter.incrementFor(Status.PASSED);
        statusCounter.incrementFor(Status.FAILED);

        // then
        assertThat(statusCounter.size()).isEqualTo(2);
    }