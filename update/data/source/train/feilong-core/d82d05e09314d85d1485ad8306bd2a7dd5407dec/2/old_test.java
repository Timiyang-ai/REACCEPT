@Test
    public void testGetSubMapExcludeKeys(){

        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("a", 3007);
        map.put("b", 3001);
        map.put("c", 3002);
        map.put("d", 3003);
        map.put("e", 3004);
        map.put("f", 3005);
        map.put("g", -1005);

        String[] keys = { "a", "g", "m" };
        Map<String, Integer> subMapExcludeKeys = MapUtil.getSubMapExcludeKeys(map, keys);

        LOGGER.debug(JsonUtil.format(subMapExcludeKeys));
    }