@Test
    public void testLtProp() {
    	assertEquals(Criteria.ltProp("id", "name"), instance.ltProp("id", "name").getQueryCriterion());
    }