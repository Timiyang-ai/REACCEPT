@Test
    public void testSizeNotEq() {
    	assertEquals(Criteria.sizeNotEq("id", 3), instance.sizeNotEq("id", 3).getQueryCriterion());
    }