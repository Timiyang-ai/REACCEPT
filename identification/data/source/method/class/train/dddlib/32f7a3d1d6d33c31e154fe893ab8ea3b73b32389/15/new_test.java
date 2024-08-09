@Test
    public void testIsFalse() {
    	assertEquals(new EqCriterion("name", false), instance.isFalse("name").getQueryCriterion());
    }