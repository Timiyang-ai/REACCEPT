@Test
    public void testContainsText() {
    	assertEquals(new ContainsTextCriterion("name", "a"), instance.containsText("name", "a").getQueryCriterion());
    }