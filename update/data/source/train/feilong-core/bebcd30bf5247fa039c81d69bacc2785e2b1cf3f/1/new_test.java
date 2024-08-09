@Test
    public void testSort(){
        Map<String, Integer> map = new LinkedHashMap<>();

        map.put("a8", 8);
        map.put("a13", 123);
        map.put("a2", 345);

        Map<String, Integer> sort = sortMap(
                        map,
                        new PropertyComparator<Map.Entry<String, Integer>>("key", new RegexGroupNumberComparator("a(\\d*)")));
        assertThat(sort.keySet(), contains("a2", "a8", "a13"));
    }