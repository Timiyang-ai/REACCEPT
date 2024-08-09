    @Test
    public void isUnknown() throws Exception {
        assertTrue(Version.UNKNOWN.isUnknown());
        assertTrue(Version.of(UNKNOWN_VERSION, UNKNOWN_VERSION).isUnknown());
        assertTrue(Version.of(0, 0).isUnknown());
    }