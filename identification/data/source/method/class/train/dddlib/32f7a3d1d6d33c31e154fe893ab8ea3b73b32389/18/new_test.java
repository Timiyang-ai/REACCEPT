@Test
    public void testGtProp() {
    	assertEquals(new GtPropCriterion("id", "name"), instance.gtProp("id", "name").getQueryCriterion());
    }