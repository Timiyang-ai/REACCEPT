@Test
    public void testGeProp() {
    	assertEquals(new GePropCriterion("id", "name"), instance.geProp("id", "name").getQueryCriterion());
    }