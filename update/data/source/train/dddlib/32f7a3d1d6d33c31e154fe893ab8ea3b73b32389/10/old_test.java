@Test
    public void testGeProp() {
        assertTrue(instance.geProp("id", "name").getQueryCriterions()
                .contains(new GePropCriterion("id", "name")));
    }