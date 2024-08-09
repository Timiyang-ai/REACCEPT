@Test
    public void testSetFormat() throws SQLException
    {
        int id = 3;
        BitstreamFormat format = bitstreamFormatService.find(context, id);
        bs.setFormat(format);
        assertThat("testSetFormat 0", bs.getFormat(context), notNullValue());
        assertThat("testSetFormat 1", bs.getFormat(context), equalTo(bitstreamFormatService.find(context, id)));
    }