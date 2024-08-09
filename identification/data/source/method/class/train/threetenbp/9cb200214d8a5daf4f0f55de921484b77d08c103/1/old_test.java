@Test
    public void test_getVersions_String() {
        NavigableMap<String, ZoneRules> versions = ZoneRulesProvider.getVersions("Europe/London");
        assertTrue(versions.size() >= 1);
        ZoneRules rules = ZoneRulesProvider.getRules("Europe/London", false);
        assertEquals(versions.lastEntry().getValue(), rules);

        NavigableMap<String, ZoneRules> copy = new TreeMap<>(versions);
        versions.clear();
        assertEquals(versions.size(), 0);
        NavigableMap<String, ZoneRules> versions2 = ZoneRulesProvider.getVersions("Europe/London");
        assertEquals(versions2, copy);
    }