@Test
    public void testLeProp() {
        assertTrue(instance.leProp("id", "name").getQueryCriterions()
                .contains(new LePropCriterion("id", "name")));
    }