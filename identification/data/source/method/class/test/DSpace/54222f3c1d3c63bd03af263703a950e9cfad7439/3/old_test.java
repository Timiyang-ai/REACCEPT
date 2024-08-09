@Test
    public void testGetFormat() throws SQLException
    {
        assertThat("testGetFormat 0", bs.getFormat(), notNullValue());
        assertThat("testGetFormat 1", bs.getFormat(), equalTo(BitstreamFormat.findUnknown(context)));
    }