@Test
    public void testNot() {
        QueryCriterion criterion1 = Criteria.eq("name", "abc");
        QueryCriterion criterion2 = Criteria.not(criterion1);
        
        assertEquals(criterion2, instance.not(criterion1).getQueryCriterion());
    }