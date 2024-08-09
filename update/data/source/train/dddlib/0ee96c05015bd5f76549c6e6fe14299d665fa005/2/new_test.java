@Test
    public void testFindByProperties() {
        MapParameters params = MapParameters.create()
                .add("category", education)
                .add("code", "02");
        List<Dictionary> results = repository.findByProperties(Dictionary.class, params);
        assertTrue(results.contains(graduate));
        assertFalse(results.contains(undergraduate));
        assertFalse(results.contains(male));
    }