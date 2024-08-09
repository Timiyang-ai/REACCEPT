@Test
    public void testAnd() {
        QueryCriterion criterion1 = new EqCriterion("name", "abc");
        QueryCriterion criterion2 = new GtCriterion("id", 5);
        QueryCriterion criterion3 = new AndCriterion(criterion1, criterion2);
        
        assertEquals(criterion3, instance.and(criterion1, criterion2).getQueryCriterion());
    }