@Test
    public void testIsNull() {
    	assertEquals(new IsNullCriterion("name"), instance.isNull("name").getQueryCriterion());
    }