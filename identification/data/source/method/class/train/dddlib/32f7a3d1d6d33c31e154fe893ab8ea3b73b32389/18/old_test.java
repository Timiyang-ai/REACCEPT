@Test
    public void testGtProp() {
        assertTrue(instance.gtProp("id", "name").getQueryCriterions()
                .contains(new GtPropCriterion("id", "name")));
    }