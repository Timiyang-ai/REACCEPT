@Test(groups={"tck"})
    public void test_getAvailableGroupIds() {
        Set<String> groups = ZoneRulesProvider.getAvailableGroupIds();
        assertEquals(groups.contains("TZDB"), true);
        groups.clear();
        assertEquals(groups.size(), 0);
        Set<String> groups2 = ZoneRulesProvider.getAvailableGroupIds();
        assertEquals(groups2.contains("TZDB"), true);
    }