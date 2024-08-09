    @Test
    public void get() throws Exception {
        facts.put("foo", 1);
        assertThat(facts.get("foo")).isEqualTo(1);
    }