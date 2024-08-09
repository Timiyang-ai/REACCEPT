@Test
    public void combineQueryString(){
        Map<String, String[]> keyAndArrayMap = new HashMap<String, String[]>();
        keyAndArrayMap.put("a", new String[] { "aaaa", "bbbb" });
        String charsetType = CharsetType.UTF8;
        LOGGER.info(ParamUtil.parseArrayValueMapToQueryString(keyAndArrayMap, charsetType));
        LOGGER.info(ParamUtil.parseArrayValueMapToQueryString(null, charsetType));
        LOGGER.info(ParamUtil.parseArrayValueMapToQueryString(null, null));
        LOGGER.info(ParamUtil.parseArrayValueMapToQueryString(keyAndArrayMap, null));
    }