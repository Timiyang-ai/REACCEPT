@Test
    public void testGetFormatDescription()
    {
        //format is unknown by default
        String format = "Unknown";
        assertThat("testGetFormatDescription 0", bs.getFormatDescription(),
                notNullValue());
        assertThat("testGetFormatDescription 1", bs.getFormatDescription(),
                not(equalTo("")));
        assertThat("testGetFormatDescription 2", bs.getFormatDescription(),
                equalTo(format));
    }