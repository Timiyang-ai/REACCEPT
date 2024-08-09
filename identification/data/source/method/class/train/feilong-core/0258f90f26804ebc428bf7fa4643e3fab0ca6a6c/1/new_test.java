@Test
    public void testAddParameterSingleValueMapWithParamByReplace(){
        String beforeUrl = PATH + "?a=b&city=12345&name=feilong";
        Map<String, String> singleValueMap = new LinkedHashMap<>();

        singleValueMap.put("province", "江苏省");
        singleValueMap.put("city", "南通市");

        String expected = Slf4jUtil.format(PATH + "?a=b&city={}&name=feilong&province={}", encode("南通市", UTF8), encode("江苏省", UTF8));
        assertEquals(expected, addParameterSingleValueMap(beforeUrl, singleValueMap, UTF8));
    }