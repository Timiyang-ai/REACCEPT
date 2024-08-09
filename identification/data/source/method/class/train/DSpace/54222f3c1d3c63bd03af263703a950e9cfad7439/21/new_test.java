@Test
    public void testGetFormatDescription() throws SQLException {
        //format is unknown by default
        String format = "Unknown";
        assertThat("testGetFormatDescription 0", bs.getFormatDescription(context),
                notNullValue());
        assertThat("testGetFormatDescription 1", bs.getFormatDescription(context),
                not(equalTo("")));
        assertThat("testGetFormatDescription 2", bs.getFormatDescription(context),
                equalTo(format));
    }