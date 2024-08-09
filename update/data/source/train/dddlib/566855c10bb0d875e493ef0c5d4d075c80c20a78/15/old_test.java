@Test
    public void testLeProp() {
    	assertEquals(new LePropCriterion("id", "name"), instance.leProp("id", "name").getQueryCriterion());
    }