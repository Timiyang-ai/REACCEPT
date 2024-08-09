@Test
    public void testGetStaticProperty(){
        assertEquals(MimeType.JPG.getExtension(), FieldUtil.getStaticProperty("com.feilong.core.io.ImageType", "JPG"));
        assertEquals(TimeInterval.SECONDS_PER_WEEK, FieldUtil.getStaticProperty("com.feilong.core.date.TimeInterval", "SECONDS_PER_WEEK"));
    }