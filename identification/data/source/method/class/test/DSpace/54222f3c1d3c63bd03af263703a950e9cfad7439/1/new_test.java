@Test
    public void testGetLicenseCollection()
    {
        assertThat("testGetLicenseCollection 0", collection.getLicenseCollection(), notNullValue());
        assertThat("testGetLicenseCollection 1", collection.getLicenseCollection(), equalTo(""));
    }