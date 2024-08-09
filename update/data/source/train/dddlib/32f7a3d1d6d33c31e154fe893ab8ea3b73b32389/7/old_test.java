@Test
    public void testLtProp() {
        assertTrue(instance.ltProp("id", "name").getQueryCriterions()
                .contains(new LtPropCriterion("id", "name")));
    }