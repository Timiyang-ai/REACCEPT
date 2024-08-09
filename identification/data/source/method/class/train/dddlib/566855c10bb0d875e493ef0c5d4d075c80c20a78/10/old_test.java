@Test
    public void testSizeNotEq() {
    	assertEquals(new SizeNotEqCriterion("id", 3), instance.sizeNotEq("id", 3).getQueryCriterion());
    }