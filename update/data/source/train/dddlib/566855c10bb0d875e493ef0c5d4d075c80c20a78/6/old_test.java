@Test
    public void testSizeEq() {
    	assertEquals(new SizeEqCriterion("id", 3), instance.sizeEq("id", 3).getQueryCriterion());
    }