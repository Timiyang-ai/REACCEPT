@Test
    public void testNotEq() {
    	assertEquals(new NotEqCriterion("name", "abc"), instance.notEq("name", "abc").getQueryCriterion());
    }