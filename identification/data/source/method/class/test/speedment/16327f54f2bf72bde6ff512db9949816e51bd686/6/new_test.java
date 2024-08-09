    @Test
    void map() {
        final Set<String> expected = new HashSet<>(stringToInt.keySet());
        final Set<String> actual = instance.map(Map.Entry::getKey).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }