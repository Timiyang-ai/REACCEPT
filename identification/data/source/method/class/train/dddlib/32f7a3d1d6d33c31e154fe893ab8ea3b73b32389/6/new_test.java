@Test
    public void testIsTrue() {
    	assertEquals(new EqCriterion("name", true), instance.isTrue("name").getQueryCriterion());
    }