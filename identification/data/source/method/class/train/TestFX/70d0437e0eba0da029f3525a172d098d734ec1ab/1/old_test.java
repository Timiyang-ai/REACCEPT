    @Test
    public void timerWaitForIdle() {
        // when:
        AtomicBoolean reachedStatement = new AtomicBoolean(false);
        asyncFx(() -> {
            sleep(100, TimeUnit.MILLISECONDS);
            asyncFx(() -> reachedStatement.set(true));
        });
        WaitForAsyncUtils.waitForFxEvents();

        // then:
        assertThat(reachedStatement.get(), is(true));
    }