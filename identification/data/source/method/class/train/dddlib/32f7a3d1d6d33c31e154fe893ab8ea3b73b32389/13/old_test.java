@Test
    public void testGt() {
        assertTrue(instance.gt("id", 5).getQueryCriterions()
                .contains(new GtCriterion("id", 5)));
    }