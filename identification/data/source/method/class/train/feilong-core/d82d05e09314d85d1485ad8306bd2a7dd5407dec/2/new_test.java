@Test
    public void testGetSubMapExcludeKeys(){

        Map<String, Integer> map = new LinkedHashMap<String, Integer>();

        map.put("a", 3007);
        map.put("b", 3001);
        map.put("c", 3002);
        map.put("g", -1005);

        LOGGER.debug(JsonUtil.format(MapUtil.getSubMapExcludeKeys(map, "a", "g", "m")));
    }