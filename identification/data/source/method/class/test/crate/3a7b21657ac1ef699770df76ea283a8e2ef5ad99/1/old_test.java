    @Test
    public void test_min_version_is_4_0_0() {
        assertThat(Version.CURRENT.minimumCompatibilityVersion(), is(Version.V_4_0_0));
    }