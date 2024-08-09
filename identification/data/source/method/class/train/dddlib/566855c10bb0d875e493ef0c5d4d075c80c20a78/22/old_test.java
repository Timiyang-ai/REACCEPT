@Test
    public void testNot() {
        QueryCriterion criterion1 = new EqCriterion("name", "abc");
        QueryCriterion criterion2 = new NotCriterion(criterion1);
        
        assertEquals(criterion2, instance.not(criterion1).getQueryCriterion());
    }