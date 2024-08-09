    @Test
    public void exponential() throws Exception {
        Backoff backoff = Backoff.exponential(10, 50);
        assertThat(backoff.nextDelayMillis(1)).isEqualTo(10);
        assertThat(backoff.nextDelayMillis(2)).isEqualTo(20);
        assertThat(backoff.nextDelayMillis(3)).isEqualTo(40);
        assertThat(backoff.nextDelayMillis(4)).isEqualTo(50);
        assertThat(backoff.nextDelayMillis(5)).isEqualTo(50);

        backoff = Backoff.exponential(10, 120, 3.0);
        assertThat(backoff.nextDelayMillis(1)).isEqualTo(10);
        assertThat(backoff.nextDelayMillis(2)).isEqualTo(30);
        assertThat(backoff.nextDelayMillis(3)).isEqualTo(90);
        assertThat(backoff.nextDelayMillis(4)).isEqualTo(120);
        assertThat(backoff.nextDelayMillis(5)).isEqualTo(120);
    }