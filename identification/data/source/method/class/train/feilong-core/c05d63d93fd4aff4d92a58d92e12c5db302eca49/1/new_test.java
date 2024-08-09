@Test
    public void testToSingleValueMap(){
        Map<String, String[]> arrayValueMap = MapUtil.newLinkedHashMap(2);
        arrayValueMap.put("province", toArray("浙江省", "江苏省"));
        arrayValueMap.put("city", toArray("南通市"));

        Map<String, String> singleValueMap = MapUtil.toSingleValueMap(arrayValueMap);
        assertThat(singleValueMap, allOf(hasEntry("province", "浙江省"), hasEntry("city", "南通市")));
    }