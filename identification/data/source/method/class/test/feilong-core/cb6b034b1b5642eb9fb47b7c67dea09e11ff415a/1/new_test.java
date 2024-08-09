@Test
    public void testJoinValues(){
        String value = "create_salesorder";
        String value2 = "unionpay_mobile";

        Map<String, String> map = new HashMap<String, String>();
        map.put("service", value);
        map.put("paymentType", value2);

        assertEquals(StringUtils.EMPTY, ParamUtil.joinValuesOrderByIncludeKeys(map, "a", "b"));
        assertEquals(value, ParamUtil.joinValuesOrderByIncludeKeys(map, "service"));
        assertEquals(value + value2, ParamUtil.joinValuesOrderByIncludeKeys(map, "service", "paymentType"));
        assertEquals(value2 + value, ParamUtil.joinValuesOrderByIncludeKeys(map, "paymentType", "service"));
    }