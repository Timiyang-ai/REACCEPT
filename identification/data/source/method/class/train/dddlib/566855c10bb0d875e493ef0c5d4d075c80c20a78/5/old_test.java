@Test
    public void testSizeGe() {
    	assertEquals(new SizeGeCriterion("id", 3), instance.sizeGe("id", 3).getQueryCriterion());
    }