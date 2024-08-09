@Test
    public void testNotEqProp() {
        assertTrue(instance.notEqProp("id", "name").getQueryCriterions()
                .contains(new NotEqPropCriterion("id", "name")));
    }