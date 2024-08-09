@Test
    public void testLe() {
    	assertEquals(Criteria.le("id", 5), instance.le("id", 5).getQueryCriterion());
    }