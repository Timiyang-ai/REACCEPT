    @Test
    void peek() {
        final List<Map.Entry<String, Integer>> entries = new ArrayList<>();
        instance
            .peek((Consumer<Map.Entry<String, Integer>>) entries::add)
            .forEach(unused -> {});
        assertEquals(refStream().collect(Collectors.toList()), entries);
    }