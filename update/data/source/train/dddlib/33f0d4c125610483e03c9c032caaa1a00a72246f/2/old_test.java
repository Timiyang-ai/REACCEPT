@Test
    public void testGetByBusinessKeys() {
        MapParameters params = MapParameters.create()
                .add("category", education)
                .add("code", "02");
        Dictionary result = repository.getByBusinessKeys(Dictionary.class, params);
        assertEquals(graduate, result);
    }