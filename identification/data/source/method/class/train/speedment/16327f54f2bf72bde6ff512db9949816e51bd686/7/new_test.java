    @Test
    void comparing() {
        final Comparator<String> comparator = MapStream.comparing(s -> s.charAt(0), s -> s.charAt(1), s -> s.charAt(2));
        final List<Map.Entry<String, Integer>> expected = refStream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
        final List<Map.Entry<String, Integer>> actual = instance.sortedByKey(comparator).collect(Collectors.toList());
        assertEquals(expected, actual);

        assertThrows(NullPointerException.class, () -> MapStream.comparing(Function.identity(), null));
    }