    @Test
    public void vplss() {
        assertEquals("Cannot load VPLS configuration or unexpected configuration" +
                             "loaded", vplss, vplsAppConfig.vplss());
    }