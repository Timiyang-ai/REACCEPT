@Test
    public void testIsEmpty() {
    	assertEquals(new IsEmptyCriterion("name"), instance.isEmpty("name").getQueryCriterion());
    }