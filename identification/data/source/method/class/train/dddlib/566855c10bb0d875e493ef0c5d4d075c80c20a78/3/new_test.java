@Test
    public void testGeProp() {
    	assertEquals(Criteria.geProp("id", "name"), instance.geProp("id", "name").getQueryCriterion());
    }