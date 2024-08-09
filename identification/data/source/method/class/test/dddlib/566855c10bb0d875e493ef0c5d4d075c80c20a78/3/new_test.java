@Test
    public void testNotEqProp() {
    	assertEquals(Criteria.notEqProp("id", "name"), instance.notEqProp("id", "name").getQueryCriterion());
    }