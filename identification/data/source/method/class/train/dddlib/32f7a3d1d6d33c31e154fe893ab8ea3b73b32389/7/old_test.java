@Test
    public void testGe() {
        assertTrue(instance.ge("id", 5).getQueryCriterions()
                .contains(new GeCriterion("id", 5)));
    }