@Test
    public void testToSingleValueMap(){
        Map<String, String[]> keyAndArrayMap = new LinkedHashMap<String, String[]>();
        keyAndArrayMap.put("province", toArray("浙江省", "江苏省"));
        keyAndArrayMap.put("city", toArray("南通市"));

        Map<String, String> singleValueMap = MapUtil.toSingleValueMap(keyAndArrayMap);
        assertThat(singleValueMap, allOf(hasEntry("province", "浙江省"), hasEntry("city", "南通市")));
    }