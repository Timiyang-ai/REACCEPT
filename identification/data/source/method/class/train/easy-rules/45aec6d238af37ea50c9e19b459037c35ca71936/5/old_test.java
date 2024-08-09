    @Test
    public void unregister() throws Exception {
        DummyRule rule = new DummyRule();
        rules.register(rule);
        rules.unregister(rule);

        assertThat(rules).isEmpty();
    }