@Test
    public void testEqProp() {
    	assertEquals(new EqPropCriterion("id", "name"), instance.eqProp("id", "name").getQueryCriterion());
    }