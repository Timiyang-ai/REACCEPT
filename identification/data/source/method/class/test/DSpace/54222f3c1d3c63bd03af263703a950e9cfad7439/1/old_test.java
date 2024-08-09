@Test
    public void testGetLicenseCollection()
    {
        assertThat("testGetLicenseCollection 0", c.getLicenseCollection(), notNullValue());
        assertThat("testGetLicenseCollection 1", c.getLicenseCollection(), equalTo(""));
    }