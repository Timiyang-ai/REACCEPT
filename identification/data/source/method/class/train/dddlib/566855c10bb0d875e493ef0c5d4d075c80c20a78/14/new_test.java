@Test
    public void testNotEq() {
    	assertEquals(Criteria.notEq("name", "abc"), instance.notEq("name", "abc").getQueryCriterion());
    }