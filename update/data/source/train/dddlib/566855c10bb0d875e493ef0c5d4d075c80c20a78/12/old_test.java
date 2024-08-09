@Test
    public void testEq() {
    	assertEquals(new EqCriterion("name", "abc"), instance.eq("name", "abc").getQueryCriterion());
    }