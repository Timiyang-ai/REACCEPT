@Test
    public void testBetween() {
    	assertEquals(new BetweenCriterion("name", "a", "b"), instance.between("name", "a", "b").getQueryCriterion());
    }