@Test
    public void handleFailure()
            throws Exception {
        // Since the servers are started as single nodes thus already bootstrapped.
        assertThat(
                client.handleFailure(Collections.singleton("key"), Collections.emptySet()).get())
                .isEqualTo(true);
    }