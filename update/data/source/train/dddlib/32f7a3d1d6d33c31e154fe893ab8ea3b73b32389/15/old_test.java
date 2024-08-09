@Test
    public void testLt() {
        assertTrue(instance.lt("id", 5).getQueryCriterions()
                .contains(new LtCriterion("id", 5)));
    }