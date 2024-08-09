@Test
    public void testGetUsagePerApp() throws Exception {
        ESMetricsAccessor metrics = new ESMetricsAccessor();
        metrics.setEsClient(client);

        // data exists - all data for JBossOverlord/s-ramp-api:1.0
        UsagePerAppBean usagePerApp = metrics.getUsagePerApp("JBossOverlord", "s-ramp-api", "1.0",
                parseDate("2015-01-01"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerApp);
        Map<String, Long> expectedData = new HashMap<>();
        expectedData.put("dtgov", 29L);
        expectedData.put("rtgov", 14L);
        Assert.assertEquals(expectedData, usagePerApp.getData());

        // data exists - all data for Test/echo:1.0
        usagePerApp = metrics.getUsagePerApp("Test", "echo", "1.0",
                parseDate("2015-01-01"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerApp);
        expectedData.clear();
        expectedData.put("my-app", 136L);
        expectedData.put("app1", 78L);
        Assert.assertEquals(expectedData, usagePerApp.getData());

        // Test/echo:1.0 bounded by a different date range
        usagePerApp = metrics.getUsagePerApp("Test", "echo", "1.0",
                parseDate("2015-06-18"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerApp);
        expectedData.clear();
        Assert.assertEquals(expectedData, usagePerApp.getData());

        // data exists - all data for Test/echo:1.0
        usagePerApp = metrics.getUsagePerApp("Test", "echo", "1.0",
                parseDate("2015-06-01"),
                parseDate("2015-06-17"));
        Assert.assertNotNull(usagePerApp);
        expectedData.clear();
        expectedData.put("my-app", 136L);
        expectedData.put("app1", 78L);
        Assert.assertEquals(expectedData, usagePerApp.getData());

        // No data for API
        usagePerApp = metrics.getUsagePerApp("NA", "NA", "NA", parseDate("2015-01-01"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerApp);
        expectedData.clear();
        Assert.assertEquals(expectedData, usagePerApp.getData());
    }