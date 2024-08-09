    @Test
    public void withJitter() throws Exception {
        final Random random = new Random(1);
        final Backoff backoff = Backoff.fixed(1000).withJitter(-0.3, 0.3, () -> random);
        assertThat(backoff.nextDelayMillis(1)).isEqualTo(1240);
        assertThat(backoff.nextDelayMillis(2)).isEqualTo(771);
        assertThat(backoff.nextDelayMillis(3)).isEqualTo(803);
    }