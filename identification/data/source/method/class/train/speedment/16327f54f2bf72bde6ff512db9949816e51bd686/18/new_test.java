    @Test
    void forEach() {
        final Set<Map.Entry<String, Integer>> entries = new HashSet<>();
        instance
            .forEach((Consumer<Map.Entry<String, Integer>>) entries::add);
        assertEquals(refStream().collect(Collectors.toSet()), entries);
    }