@Test
    public void isAttached() {
        VplsConfig vpls = createNewVpls();

        assertTrue("Interface not correctly attached to the VPLS",
                   vpls.isAttached(IF4));
        assertFalse("Unexpected interface attached to the VPLS",
                    vpls.isAttached(IF_NON_EXIST));
    }