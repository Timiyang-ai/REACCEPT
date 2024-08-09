@Test
    public void testGe() {
    	assertEquals(new GeCriterion("id", 5), instance.ge("id", 5).getQueryCriterion());
    }