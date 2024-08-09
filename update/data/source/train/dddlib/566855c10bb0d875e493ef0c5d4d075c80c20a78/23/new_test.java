@Test
    public void testNotEmpty() {
    	assertEquals(Criteria.notEmpty("name"), instance.notEmpty("name").getQueryCriterion());
    }