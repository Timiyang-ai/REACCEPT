@Test
    public void testParseSuppressionRules() throws Exception {
        //File file = new File(this.getClass().getClassLoader().getResource("suppressions.xml").getPath());
        File file = BaseTest.getResourceAsFile(this, "suppressions.xml");
        SuppressionParser instance = new SuppressionParser();
        List<SuppressionRule> result = instance.parseSuppressionRules(file);
        assertTrue(result.size() > 3);
    }