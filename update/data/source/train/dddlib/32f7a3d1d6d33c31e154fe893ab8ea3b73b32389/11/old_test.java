@Test
    public void testEqProp() {
        assertTrue(instance.eqProp("id", "name").getQueryCriterions()
                .contains(new EqPropCriterion("id", "name")));
    }