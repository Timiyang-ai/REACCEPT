@Test
    public void testGetByBusinessKeys() {
        NamedParameters params = NamedParameters.create()
                .add("category", education)
                .add("code", "02");
        Dictionary result = repository.getByBusinessKeys(Dictionary.class, params);
        assertEquals(graduate, result);
    }