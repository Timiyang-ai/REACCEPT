    @Test
    void distinctValues() {
        final MapStream<String, Integer> ms = MapStream.of(entry("jedan", 1), entry("Eins", 1));
        assertEquals(1, ms.distinctValues().count());
    }