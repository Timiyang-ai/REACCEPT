@Test
    public void testSetAuthKey() throws Exception {
        ospfInterface.setAuthKey("00");
        assertThat(ospfInterface.authKey(), is("00"));
    }