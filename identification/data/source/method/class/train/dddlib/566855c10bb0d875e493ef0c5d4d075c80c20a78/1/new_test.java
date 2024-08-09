@Test
    public void testLeProp() {
    	assertEquals(Criteria.leProp("id", "name"), instance.leProp("id", "name").getQueryCriterion());
    }