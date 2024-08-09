@Test
    public void testNotEqProp() {
    	assertEquals(new NotEqPropCriterion("id", "name"), instance.notEqProp("id", "name").getQueryCriterion());
    }