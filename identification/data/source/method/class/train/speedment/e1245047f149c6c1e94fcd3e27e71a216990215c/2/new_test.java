    @Test
    void sorted() {
        final List<Map.Entry<String, Integer>> expected = refStream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
        final List<Map.Entry<String, Integer>> actual = instance.sorted().toList();
        assertEquals(expected, actual);

        final MapStream<Object, Integer> ms = MapStream.of(entry(new Object(), 1), entry(new Object(), 2));
        assertThrows(UnsupportedOperationException.class, () -> ms.sorted().forEach(e -> {}));

        final MapStream<String, Integer> ms2 = MapStream.of(entry(null, 1), entry(null, 2));
        assertDoesNotThrow(() -> ms2.sorted().forEach(e -> {}));

        // Todo: Test values with both null and string values

    }