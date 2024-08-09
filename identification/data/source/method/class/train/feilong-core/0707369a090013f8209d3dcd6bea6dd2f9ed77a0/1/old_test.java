@Test
    public void testSortByKeyAsc(){
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("a", 123);
        map.put("c", 345);
        map.put("b", 8);

        Map<String, Integer> sortByKeyAsc = sortByKeyAsc(map);
        assertThat(sortByKeyAsc.keySet(), contains("a", "b", "c"));
    }