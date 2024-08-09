@Test
    public void testAddParameterSingleValueMap2(){
        String beforeUrl = "www.baidu.com?a=b";
        Map<String, String> singleValueMap = new LinkedHashMap<String, String>();

        singleValueMap.put("province", "江苏省");
        singleValueMap.put("city", "南通市");

        LOGGER.debug(ParamUtil.addParameterSingleValueMap(beforeUrl, singleValueMap, UTF8));
    }