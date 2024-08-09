@Test
    public void testSizeGt() {
    	assertEquals(Criteria.sizeGt("id", 3), instance.sizeGt("id", 3).getQueryCriterion());
    }