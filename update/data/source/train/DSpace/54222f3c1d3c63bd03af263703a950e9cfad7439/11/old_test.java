@Test
    public void testSetFormat() throws SQLException
    {
        int id = 3;
        BitstreamFormat format = BitstreamFormat.find(context, id);
        bs.setFormat(format);
        assertThat("testSetFormat 0", bs.getFormat(), notNullValue());
        assertThat("testSetFormat 1", bs.getFormat(), equalTo(BitstreamFormat.find(context, id)));
    }