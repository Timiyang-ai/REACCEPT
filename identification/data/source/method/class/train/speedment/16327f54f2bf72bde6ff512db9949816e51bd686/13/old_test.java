    @Test
    void sortedByKey() {
        final Comparator<Map.Entry<String, Integer>> comparator = Map.Entry.comparingByKey(); // Java's type inference can be improved...
        final List<Map.Entry<String, Integer>> expected = refStream().sorted(comparator.reversed()).collect(Collectors.toList());
        final List<Map.Entry<String, Integer>> actual = instance.sortedByKey(Comparator.reverseOrder()).toList();
        assertEquals(expected, actual);
    }