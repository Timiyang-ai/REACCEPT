    @Test
    public void remove() throws Exception {
        facts.put("foo", 1);
        facts.remove("foo");

        assertThat(facts).isEmpty();
    }