@Test
    public void testIsFalse() {
    	assertEquals(Criteria.eq("name", false), instance.isFalse("name").getQueryCriterion());
    }