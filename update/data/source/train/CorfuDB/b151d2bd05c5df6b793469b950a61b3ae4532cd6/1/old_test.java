@Test
    public void handleFailure()
            throws Exception {

        // Since the servers are started as single nodes thus already bootstrapped.
        Set<String> set = new HashSet<>();
        set.add("Key");
        assertThat(client.handleFailure(set).get()).isEqualTo(true);
    }