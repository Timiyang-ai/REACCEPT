@Test
    public void testSizeEq() {
    	assertEquals(Criteria.sizeEq("id", 3), instance.sizeEq("id", 3).getQueryCriterion());
    }