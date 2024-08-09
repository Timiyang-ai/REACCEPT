@Test
    public void testNotEq() {
        assertTrue(instance.notEq("name", "abc").getQueryCriterions()
                .contains(new NotEqCriterion("name", "abc")));
    }