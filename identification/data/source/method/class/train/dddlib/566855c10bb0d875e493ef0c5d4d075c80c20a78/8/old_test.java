@Test
    public void testGt() {
    	assertEquals(new GtCriterion("id", 5), instance.gt("id", 5).getQueryCriterion());
    }