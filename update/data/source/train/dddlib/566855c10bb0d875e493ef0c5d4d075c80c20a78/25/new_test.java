@Test
    public void testEqProp() {
    	assertEquals(Criteria.eqProp("id", "name"), instance.eqProp("id", "name").getQueryCriterion());
    }