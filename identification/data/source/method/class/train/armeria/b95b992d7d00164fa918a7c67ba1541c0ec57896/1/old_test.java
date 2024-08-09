    @Test
    public void successfulResponseLogLevel() {
        assertThatThrownBy(() -> builder.successfulResponseLogLevel(null))
                .isInstanceOf(NullPointerException.class);
        assertThat(builder.successfulResponseLogLevel()).isEqualTo(LogLevel.TRACE);

        builder.successfulResponseLogLevel(LogLevel.ERROR);
        assertThat(builder.successfulResponseLogLevel()).isEqualTo(LogLevel.ERROR);
    }