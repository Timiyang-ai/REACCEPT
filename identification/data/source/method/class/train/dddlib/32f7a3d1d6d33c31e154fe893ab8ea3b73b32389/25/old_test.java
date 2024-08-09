@Test
    public void testLe() {
        assertTrue(instance.le("id", 5).getQueryCriterions()
                .contains(new LeCriterion("id", 5)));
    }