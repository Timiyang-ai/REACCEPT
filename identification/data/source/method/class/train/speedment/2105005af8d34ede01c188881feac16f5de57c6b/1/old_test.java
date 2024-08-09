    @Test
    void distinctKeys() {
        final MapStream<String, Integer> ms = MapStream.of(entry("Car", 1), entry("Car", 2));
        assertEquals(1, ms.distinctKeys().count());
        final MapStream<String, Integer> ms2 = MapStream.of(entry("Car", 1), entry("Car", 2)).parallel();
        assertEquals(1, ms2.distinctKeys().count());
    }