@Test
    public void testSetAuthType() throws Exception {
        ospfInterface.setAuthType("00");
        assertThat(ospfInterface.authType(), is("00"));
    }