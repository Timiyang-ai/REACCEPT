@Test
    public void testGtProp() {
    	assertEquals(Criteria.gtProp("id", "name"), instance.gtProp("id", "name").getQueryCriterion());
    }