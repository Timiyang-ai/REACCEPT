@Test
    public void testLe() {
    	assertEquals(new LeCriterion("id", 5), instance.le("id", 5).getQueryCriterion());
    }