@Test
    public void testSizeGt() {
    	assertEquals(new SizeGtCriterion("id", 3), instance.sizeGt("id", 3).getQueryCriterion());
    }