    @Test
    void forEachOrdered() {
        final List<Map.Entry<String, Integer>> entries = new ArrayList<>();
        instance
            .forEachOrdered((Consumer<Map.Entry<String, Integer>>) entries::add);
        assertEquals(refStream().collect(Collectors.toList()), entries);
    }