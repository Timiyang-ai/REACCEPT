@Test
    public void testJoinSingleValueMap(){

        Map<String, String> map = new HashMap<String, String>();

        map.put(null, null);
        //        map.put("a", "");
        //        map.put("b", null);
        //        map.put("c", "jim");
        LOGGER.info(ParamUtil.toQueryStringUseSingleValueMap(map));
    }