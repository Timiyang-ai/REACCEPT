    @Test
    public void register() throws Exception {
        rules.register(new DummyRule());

        assertThat(rules).hasSize(1);
    }