@Test
    public void testConstructSubMap(){
        Map<Long, User> map = new LinkedHashMap<Long, User>();
        map.put(1L, new User(1L));
        map.put(2L, new User(2L));
        map.put(53L, new User(3L));
        map.put(5L, new User(5L));
        map.put(6L, new User(6L));
        map.put(4L, new User(4L));

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug(JsonUtil.format(MapUtil.extractSubMap(map, "id", Long.class)));
        }
        if (LOGGER.isDebugEnabled()){
            Long[] includeKeys = { 5L, 4L };
            LOGGER.debug(JsonUtil.format(MapUtil.extractSubMap(map, includeKeys, "id", Long.class)));
        }
        if (LOGGER.isDebugEnabled()){
            Long[] includeKeys = { 5L, 4L };
            LOGGER.debug(JsonUtil.format(MapUtil.extractSubMap(map, includeKeys, "userInfo.age", Long.class)));
        }
    }