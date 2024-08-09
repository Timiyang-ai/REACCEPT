@Test
    public void testContainsText() {
        assertTrue(instance.containsText("name", "a").getQueryCriterions()
                .contains(Criteria.containsText("name", "a")));
    }