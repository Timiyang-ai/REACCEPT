@Test
    public void testGetUsagePerClient() throws Exception {
        ESMetricsAccessor metrics = new ESMetricsAccessor();
        metrics.setEsClient(client);

        // data exists - all data for JBossOverlord/s-ramp-api:1.0
        UsagePerClientBean usagePerClient = metrics.getUsagePerClient("JBossOverlord", "s-ramp-api", "1.0",
                parseDate("2015-01-01"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerClient);
        Map<String, Long> expectedData = new HashMap<>();
        expectedData.put("dtgov", 29L);
        expectedData.put("rtgov", 14L);
        Assert.assertEquals(expectedData, usagePerClient.getData());

        // data exists - all data for Test/echo:1.0
        usagePerClient = metrics.getUsagePerClient("Test", "echo", "1.0",
                parseDate("2015-01-01"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerClient);
        expectedData.clear();
        expectedData.put("my-client", 136L);
        expectedData.put("client1", 78L);
        Assert.assertEquals(expectedData, usagePerClient.getData());

        // Test/echo:1.0 bounded by a different date range
        usagePerClient = metrics.getUsagePerClient("Test", "echo", "1.0",
                parseDate("2015-06-18"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerClient);
        expectedData.clear();
        Assert.assertEquals(expectedData, usagePerClient.getData());

        // data exists - all data for Test/echo:1.0
        usagePerClient = metrics.getUsagePerClient("Test", "echo", "1.0",
                parseDate("2015-06-01"),
                parseDate("2015-06-17"));
        Assert.assertNotNull(usagePerClient);
        expectedData.clear();
        expectedData.put("my-client", 136L);
        expectedData.put("client1", 78L);
        Assert.assertEquals(expectedData, usagePerClient.getData());

        // No data for API
        usagePerClient = metrics.getUsagePerClient("NA", "NA", "NA", parseDate("2015-01-01"), new DateTime().withZone(DateTimeZone.UTC));
        Assert.assertNotNull(usagePerClient);
        expectedData.clear();
        Assert.assertEquals(expectedData, usagePerClient.getData());
    }