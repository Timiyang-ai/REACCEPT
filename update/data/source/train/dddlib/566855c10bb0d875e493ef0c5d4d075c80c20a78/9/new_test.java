@Test
    public void testIsTrue() {
    	assertEquals(Criteria.eq("name", true), instance.isTrue("name").getQueryCriterion());
    }