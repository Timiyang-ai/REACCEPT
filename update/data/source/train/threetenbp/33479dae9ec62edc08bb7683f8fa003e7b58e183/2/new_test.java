@Test(groups={"tck"})
    public void test_registerProvider() {
        Set<String> pre = ZoneRulesProvider.getAvailableZoneIds();
        assertEquals(pre.contains("FooLocation"), false);
        ZoneRulesProvider.registerProvider(new MockTempProvider());
        assertEquals(pre.contains("FooLocation"), false);
        Set<String> post = ZoneRulesProvider.getAvailableZoneIds();
        assertEquals(post.contains("FooLocation"), true);

        assertEquals(ZoneRulesProvider.getRules("FooLocation"), ZoneOffset.of("+01:45").getRules());
    }