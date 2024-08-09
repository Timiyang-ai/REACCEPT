    @Test
    void failureResponseLogLevel() {
        assertThatThrownBy(() -> builder.failureResponseLogLevel(null))
                .isInstanceOf(NullPointerException.class);
        assertThat(builder.failedResponseLogLevel()).isEqualTo(LogLevel.WARN);

        builder.failureResponseLogLevel(LogLevel.ERROR);
        assertThat(builder.failedResponseLogLevel()).isEqualTo(LogLevel.ERROR);
    }