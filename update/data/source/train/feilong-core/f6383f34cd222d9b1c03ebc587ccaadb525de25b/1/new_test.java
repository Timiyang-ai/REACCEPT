@Test
    public void readPrefixAsMap(){
        Map<String, String> map = ResourceBundleUtil.readPrefixAsMap(BASE_NAME, "FileType", ".", Locale.CHINA);
        LOGGER.info(JsonUtil.format(map));
    }