@Test
    public void testLeProp() {
        assertTrue(instance.leProp("id", "name").getQueryCriterions()
                .contains(Criteria.leProp("id", "name")));
    }