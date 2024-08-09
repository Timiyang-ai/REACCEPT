@Test
    public void testSizeLe() {
        assertTrue(instance.sizeLe("id", 3).getQueryCriterions()
                .contains(new SizeLeCriterion("id", 3)));
    }