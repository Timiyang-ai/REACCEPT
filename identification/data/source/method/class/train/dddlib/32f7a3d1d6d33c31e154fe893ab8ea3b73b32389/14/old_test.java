@Test
    public void testIsEmpty() {
        assertTrue(instance.isEmpty("name").getQueryCriterions()
                .contains(new IsEmptyCriterion("name")));
    }