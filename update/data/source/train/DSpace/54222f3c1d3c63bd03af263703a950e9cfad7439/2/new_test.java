@Test
    public void testGetBundles() throws SQLException
    {
        assertThat("testGetBundles 0", bs.getBundles(), notNullValue());
        //by default no bundles
        assertTrue("testGetBundles 1", bs.getBundles().size() == 0);
    }