@Test
    public void testEq() {
    	assertEquals(Criteria.eq("name", "abc"), instance.eq("name", "abc").getQueryCriterion());
    }