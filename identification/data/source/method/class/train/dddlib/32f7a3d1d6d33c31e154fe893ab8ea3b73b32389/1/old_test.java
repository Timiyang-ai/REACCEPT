@Test
    public void testNotEmpty() {
        assertTrue(instance.notEmpty("name").getQueryCriterions()
                .contains(new NotEmptyCriterion("name")));
    }