@Test
    public void testJoinValues(){
        String value = "create_salesorder";
        String value2 = "unionpay_mobile";

        Map<String, String> map = new HashMap<String, String>();
        map.put("service", value);
        map.put("paymentType", value2);

        assertEquals(StringUtils.EMPTY, ParamUtil.joinValues(map, "a", "b"));
        assertEquals(value, ParamUtil.joinValues(map, "service"));
        assertEquals(value + value2, ParamUtil.joinValues(map, "service", "paymentType"));
        assertEquals(value2 + value, ParamUtil.joinValues(map, "paymentType", "service"));
    }