    @Test
    void reduce() {
        final Optional<Map.Entry<String, Integer>> expected = Optional.of(entry("jedandvatri", 1 + 2 + 3));
        final Optional<Map.Entry<String, Integer>> actual = instance.reduce((e0, e1) -> entry(e0.getKey()+e1.getKey() , e0.getValue()+e1.getValue()));
        assertEquals(expected, actual);
    }