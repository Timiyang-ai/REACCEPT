@Test
    public void testGetFormat() throws SQLException
    {
        assertThat("testGetFormat 0", bs.getFormat(context), notNullValue());
        assertThat("testGetFormat 1", bs.getFormat(context), equalTo(bitstreamFormatService.findUnknown(context)));
    }