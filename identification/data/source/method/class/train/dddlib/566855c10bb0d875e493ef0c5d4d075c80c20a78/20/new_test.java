@Test
    public void testNotNull() {
    	assertEquals(Criteria.notNull("name"), instance.notNull("name").getQueryCriterion());
    }