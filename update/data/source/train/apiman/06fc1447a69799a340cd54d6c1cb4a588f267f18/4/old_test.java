@Test
    public void testGetAppUsagePerApi() throws Exception {
        ESMetricsAccessor metrics = new ESMetricsAccessor();
        metrics.setEsClient(client);

        // data exists - all data for JBossOverlord/s-ramp-api:1.0
        AppUsagePerApiBean usagePerApi = metrics.getAppUsagePerApi("JBossOverlord", "dtgov", "1.0",
                parseDate("2015-01-01"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerApi);
        Map<String, Long> expectedData = new HashMap<>();
        expectedData.put("s-ramp-api", 29L);
        Assert.assertEquals(expectedData, usagePerApi.getData());

        usagePerApi = metrics.getAppUsagePerApi("JBossOverlord", "rtgov", "1.0",
                parseDate("2015-01-01"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerApi);
        expectedData = new HashMap<>();
        expectedData.put("s-ramp-api", 14L);
        Assert.assertEquals(expectedData, usagePerApi.getData());
    }