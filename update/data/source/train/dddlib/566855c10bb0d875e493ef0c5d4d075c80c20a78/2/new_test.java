@Test
    public void testGt() {
    	assertEquals(Criteria.gt("id", 5), instance.gt("id", 5).getQueryCriterion());
    }