@Test
    public void testFindByProperties() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("category", education);
        params.put("code", "02");
        List<Dictionary> results = repository.findByProperties(Dictionary.class, params);
        assertTrue(results.contains(graduate));
        assertFalse(results.contains(undergraduate));
        assertFalse(results.contains(male));
    }