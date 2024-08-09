@Test
    public void testNotEmpty() {
    	assertEquals(new NotEmptyCriterion("name"), instance.notEmpty("name").getQueryCriterion());
    }