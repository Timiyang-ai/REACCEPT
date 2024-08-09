@Test
    public void testNot() {
        QueryCriterion criterion1 = new EqCriterion("name", "abc");
        QueryCriterion criterion2 = new NotCriterion(criterion1);
        
        assertTrue(instance.not(criterion1).getQueryCriterions()
                .contains(criterion2));
    }