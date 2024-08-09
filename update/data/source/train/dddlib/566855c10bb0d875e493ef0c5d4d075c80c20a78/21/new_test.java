@Test
    public void testLt() {
    	assertEquals(Criteria.lt("id", 5), instance.lt("id", 5).getQueryCriterion());
    }