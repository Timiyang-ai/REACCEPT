@Test
    public void testGetLicense() 
    {
        assertThat("testGetLicense 0", c.getLicense(), notNullValue());
        assertThat("testGetLicense 1", c.getLicense(), equalTo(ConfigurationManager.getDefaultSubmissionLicense()));
    }