@Test
    public void testLt() {
    	assertEquals(new LtCriterion("id", 5), instance.lt("id", 5).getQueryCriterion());
    }