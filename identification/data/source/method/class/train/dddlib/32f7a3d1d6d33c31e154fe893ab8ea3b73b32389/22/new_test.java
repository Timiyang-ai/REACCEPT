@Test
    public void testLtProp() {
    	assertEquals(new LtPropCriterion("id", "name"), instance.ltProp("id", "name").getQueryCriterion());
    }