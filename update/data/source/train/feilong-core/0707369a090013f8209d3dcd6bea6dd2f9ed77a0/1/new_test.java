@Test
    public void testSortByKeyAsc(){
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("a", 123);
        map.put("d", 3455);
        map.put(null, 1345);
        map.put("c", 345);
        map.put("b", 8);

        Map<String, Integer> sortByKeyAsc = sortByKeyAsc(map);
        assertThat(sortByKeyAsc.keySet(), contains(null, "a", "b", "c", "d"));
        assertThat(
                        sortByKeyAsc,
                        allOf(hasEntry("a", 123), hasEntry("b", 8), hasEntry("c", 345), hasEntry("d", 3455), hasEntry(null, 1345)));
    }