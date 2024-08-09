@Test
    public void testSizeLt() {
        assertTrue(instance.sizeLt("id", 3).getQueryCriterions()
                .contains(new SizeLtCriterion("id", 3)));
    }