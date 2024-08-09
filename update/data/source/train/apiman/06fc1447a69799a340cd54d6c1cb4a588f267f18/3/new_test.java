@Test
    public void testGetResponseStatsPerClient() throws Exception {
        ESMetricsAccessor metrics = new ESMetricsAccessor();
        metrics.setEsClient(client);

        // s-ramp-api data
        ResponseStatsPerClientBean stats = metrics.getResponseStatsPerClient("JBossOverlord", "s-ramp-api", "1.0",
                parseDate("2015-06-01"), new DateTime().withZone(DateTimeZone.UTC));
        Map<String, ResponseStatsDataPoint> data = stats.getData();
        Assert.assertEquals(2, data.size());
        ResponseStatsDataPoint point = data.get("dtgov");
        Assert.assertNotNull(point);
        Assert.assertEquals(29L, point.getTotal());
        Assert.assertEquals(0L, point.getFailures());
        Assert.assertEquals(0L, point.getErrors());
        point = data.get("rtgov");
        Assert.assertNotNull(point);
        Assert.assertEquals(14L, point.getTotal());
        Assert.assertEquals(0L, point.getFailures());
        Assert.assertEquals(3L, point.getErrors());

        // test/echo data
        stats = metrics.getResponseStatsPerClient("Test", "echo", "1.0",
                parseDate("2015-06-01"), new DateTime().withZone(DateTimeZone.UTC));
        data = stats.getData();
        Assert.assertEquals(2, data.size());
        point = data.get("client1");
        Assert.assertNotNull(point);
        Assert.assertEquals(78L, point.getTotal());
        Assert.assertEquals(19L, point.getFailures());
        Assert.assertEquals(1L, point.getErrors());
        point = data.get("my-client");
        Assert.assertNotNull(point);
        Assert.assertEquals(136L, point.getTotal());
        Assert.assertEquals(22L, point.getFailures());
        Assert.assertEquals(1L, point.getErrors());
    }