@Test
    public void testStartsWithText() {
    	assertEquals(new StartsWithTextCriterion("name", "a"), instance.startsWithText("name", "a").getQueryCriterion());
    }