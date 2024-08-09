@Test(groups={"tck"})
    public void test_registerProvider() {
        Set<String> pre = ZoneRulesProvider.getAvailableGroupIds();
        assertEquals(pre.contains("TEMPMOCK.-_"), false);
        ZoneRulesProvider.registerProvider(new MockTempProvider());
        assertEquals(pre.contains("TEMPMOCK.-_"), false);
        Set<String> post = ZoneRulesProvider.getAvailableGroupIds();
        assertEquals(post.contains("TEMPMOCK.-_"), true);

        assertEquals(ZoneRulesProvider.getProvider("TEMPMOCK.-_").getGroupId(), "TEMPMOCK.-_");
        assertEquals(ZoneRulesProvider.getProvider("TEMPMOCK.-_").getRules("World%@~.-_", "1.-_").isFixedOffset(), true);
    }