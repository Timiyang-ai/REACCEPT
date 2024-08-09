@Test
    public void testOr() {
        QueryCriterion criterion1 = new EqCriterion("name", "abc");
        QueryCriterion criterion2 = new GtCriterion("id", 5);
        QueryCriterion criterion3 = new OrCriterion(criterion1, criterion2);
        assertEquals(criterion3, instance.or(criterion1).or(criterion2).getQueryCriterion());
    }