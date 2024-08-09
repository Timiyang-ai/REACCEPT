    @Test
    void toSortedMapByKey() {
        final Comparator<String> keyComparator = Comparator.reverseOrder();
        final Map<String, Integer> expected = refStream().map(Map.Entry::getKey).sorted(keyComparator).map(k -> entry(k, stringToInt.get(k))).collect(TO_MAP);
        final Map<String, Integer> actual = instance.toSortedMapByKey(keyComparator);
        assertEquals(expected, actual);
        assertTrue(actual instanceof NavigableMap);
    }