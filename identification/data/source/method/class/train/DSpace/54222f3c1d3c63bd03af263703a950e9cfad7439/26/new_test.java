@Test
    public void testSetLicense() throws SQLException {
        String license = "license for test";
        collection.setLicense(context, license);
        assertThat("testSetLicense 0", collectionService.getLicense(collection), notNullValue());
        assertThat("testSetLicense 1", collectionService.getLicense(collection), equalTo(license));
        assertThat("testSetLicense 2", collection.getLicenseCollection(), notNullValue());
        assertThat("testSetLicense 3", collection.getLicenseCollection(), equalTo(license));
    }