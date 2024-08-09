@Test
    public void testSizeGe() {
    	assertEquals(Criteria.sizeGe("id", 3), instance.sizeGe("id", 3).getQueryCriterion());
    }