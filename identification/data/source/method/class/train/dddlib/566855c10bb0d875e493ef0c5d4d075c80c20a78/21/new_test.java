@Test
    public void testGe() {
    	assertEquals(Criteria.ge("id", 5), instance.ge("id", 5).getQueryCriterion());
    }