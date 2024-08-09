    @Test
    public void fixed() throws Exception {
        final Backoff backoff = Backoff.fixed(100);
        assertThat(backoff.nextDelayMillis(1)).isEqualTo(100);
        assertThat(backoff.nextDelayMillis(2)).isEqualTo(100);
        assertThat(backoff.nextDelayMillis(3)).isEqualTo(100);
    }