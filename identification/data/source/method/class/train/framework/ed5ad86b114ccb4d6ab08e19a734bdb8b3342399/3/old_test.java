    @Test
    public void join() {
        String s1 = "foo-bar-baz";
        String s2 = "foo--bar";

        assertEquals("foobarbaz", SharedUtil.join(s1.split("-"), ""));
        assertEquals("foo!bar!baz", SharedUtil.join(s1.split("-"), "!"));
        assertEquals("foo!!bar!!baz", SharedUtil.join(s1.split("-"), "!!"));

        assertEquals("foo##bar", SharedUtil.join(s2.split("-"), "#"));
    }