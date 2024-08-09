@Test
    public void handleFailure()
            throws Exception {

        // Since the servers are started as single nodes thus already bootstrapped.
        Map map = new HashMap<String, Boolean>();
        map.put("Key", true);
        assertThat(client.handleFailure(map).get()).isEqualTo(true);
    }