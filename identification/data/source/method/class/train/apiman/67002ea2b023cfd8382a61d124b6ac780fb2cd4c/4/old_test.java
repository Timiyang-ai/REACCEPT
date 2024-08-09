@Test
    public void testGetAppUsagePerService() throws Exception {
        ESMetricsAccessor metrics = new ESMetricsAccessor();
        metrics.setEsClient(client);

        // data exists - all data for JBossOverlord/s-ramp-api:1.0
        AppUsagePerServiceBean usagePerSvc = metrics.getAppUsagePerService("JBossOverlord", "dtgov", "1.0",
                parseDate("2015-01-01"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerSvc);
        Map<String, Long> expectedData = new HashMap<>();
        expectedData.put("s-ramp-api", 29L);
        Assert.assertEquals(expectedData, usagePerSvc.getData());

        usagePerSvc = metrics.getAppUsagePerService("JBossOverlord", "rtgov", "1.0",
                parseDate("2015-01-01"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerSvc);
        expectedData = new HashMap<>();
        expectedData.put("s-ramp-api", 14L);
        Assert.assertEquals(expectedData, usagePerSvc.getData());
    }