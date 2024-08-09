@Test
    public void testEq() {
        assertTrue(instance.eq("name", "abc").getQueryCriterions()
                .contains(new EqCriterion("name", "abc")));
    }