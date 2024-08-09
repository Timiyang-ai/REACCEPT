    @Test
    void sortedByValue() {
        final Comparator<Map.Entry<String, Integer>> comparator = Map.Entry.comparingByValue(); // Java's type inference can be improved...
        final List<Map.Entry<String, Integer>> expected = refStream().sorted(comparator.reversed()).collect(Collectors.toList());
        final List<Map.Entry<String, Integer>> actual = instance.sortedByValue(Comparator.reverseOrder()).toList();
        assertEquals(expected, actual);
    }