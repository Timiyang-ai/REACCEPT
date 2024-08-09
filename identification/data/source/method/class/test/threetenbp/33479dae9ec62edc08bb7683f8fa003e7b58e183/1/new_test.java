@Test(groups={"tck"})
    public void test_getAvailableGroupIds() {
        Set<String> zoneIds = ZoneRulesProvider.getAvailableZoneIds();
        assertEquals(zoneIds.contains("Europe/London"), true);
        zoneIds.clear();
        assertEquals(zoneIds.size(), 0);
        Set<String> zoneIds2 = ZoneRulesProvider.getAvailableZoneIds();
        assertEquals(zoneIds2.contains("Europe/London"), true);
    }