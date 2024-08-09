@Test
    public void testNotNull() {
    	assertEquals(new NotNullCriterion("name"), instance.notNull("name").getQueryCriterion());
    }