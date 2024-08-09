@Test
    public void testStartsWithText() {
    	assertEquals(Criteria.startsWithText("name", "a"), instance.startsWithText("name", "a").getQueryCriterion());
    }