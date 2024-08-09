@Test
    public final void format(){
        LOGGER.info(MessageFormatUtil.format("name=张三{0}a{1}", "jin", "xin"));
        LOGGER.info(MessageFormatUtil.format("name=张三{0,number}a{1}", 5, "xin"));
        LOGGER.info(MessageFormatUtil.format("name=张三{0,date}a{1}", 15, "xin"));
    }