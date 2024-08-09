@Test
    public void combineQueryString(){
        Map<String, String[]> keyAndArrayMap = new HashMap<String, String[]>();
        keyAndArrayMap.put("a", new String[] { "aaaa", "bbbb" });
        String charsetType = CharsetType.UTF8;
        LOGGER.info(ParamUtil.combineQueryString(keyAndArrayMap, charsetType));
        LOGGER.info(ParamUtil.combineQueryString(null, charsetType));
        LOGGER.info(ParamUtil.combineQueryString(null, null));
        LOGGER.info(ParamUtil.combineQueryString(keyAndArrayMap, null));
    }