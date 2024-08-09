@Test
    public void handleFailure()
            throws Exception {
        // Since the servers are started as single nodes thus already bootstrapped.
        assertThat(client.handleFailure(0L, Collections.singleton("key")).get()).isEqualTo(true);
    }