@Test
    public void testGetStaticProperty(){
        assertEquals(MimeType.JPG.getExtension(), FieldUtil.getStaticFieldValue("com.feilong.core.io.ImageType", "JPG"));
        assertEquals(TimeInterval.SECONDS_PER_WEEK, FieldUtil.getStaticFieldValue("com.feilong.core.date.TimeInterval", "SECONDS_PER_WEEK"));
        assertEquals(-1699987643831455524L, FieldUtil.getStaticFieldValue("com.feilong.core.bean.BeanUtilException", "serialVersionUID"));
    }