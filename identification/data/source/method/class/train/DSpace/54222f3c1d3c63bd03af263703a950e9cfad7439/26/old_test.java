@Test
    public void testSetLicense() throws SQLException {
        String license = "license for test";
        c.setLicense(license);
        assertThat("testSetLicense 0", c.getLicense(), notNullValue());
        assertThat("testSetLicense 1", c.getLicense(), equalTo(license));
        assertThat("testSetLicense 2", c.getLicenseCollection(), notNullValue());
        assertThat("testSetLicense 3", c.getLicenseCollection(), equalTo(license));
    }