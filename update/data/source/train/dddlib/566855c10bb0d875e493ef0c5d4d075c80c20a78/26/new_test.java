@Test
    public void testContainsText() {
    	assertEquals(Criteria.containsText("name", "a"), instance.containsText("name", "a").getQueryCriterion());
    }