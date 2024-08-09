@Test
    public void testContainsText() {
        assertTrue(instance.containsText("name", "a").getQueryCriterions()
                .contains(new ContainsTextCriterion("name", "a")));
    }