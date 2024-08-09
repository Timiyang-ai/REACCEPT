@Test
    public void testGetUsage() throws Exception {
        ESMetricsAccessor metrics = new ESMetricsAccessor();
        metrics.setEsClient(client);

        UsageHistogramBean usage = metrics.getUsage("JBossOverlord", "s-ramp-api", "1.0", UsageHistogramIntervalType.day,
                parseDate("2015-01-01"), DateTime.now().withZone(DateTimeZone.UTC));
        List<UsageDataPoint> data = usage.getData();
        Assert.assertTrue(data.size() > 0);
        Assert.assertTrue(data.size() > 1);
        Assert.assertEquals("2015-06-19T00:00:00.000Z", usage.getData().get(169).getLabel());
        Assert.assertEquals(46L, usage.getData().get(169).getCount());


        usage = metrics.getUsage("JBossOverlord", "s-ramp-api", "1.0", UsageHistogramIntervalType.hour,
                parseDate("2015-06-15"), parseDate("2015-06-22"));
        data = usage.getData();
        Assert.assertEquals(168, data.size());
        Assert.assertEquals("2015-06-19T15:00:00.000Z", usage.getData().get(111).getLabel());
        Assert.assertEquals(46L, usage.getData().get(111).getCount());


        usage = metrics.getUsage("JBossOverlord", "s-ramp-api", "1.0", UsageHistogramIntervalType.minute,
                parseDate("2015-06-19"), parseDate("2015-06-20"));
        data = usage.getData();
        Assert.assertEquals(1440, data.size());
        Assert.assertEquals("2015-06-19T15:13:00.000Z", usage.getData().get(913).getLabel());
        Assert.assertEquals("2015-06-19T15:14:00.000Z", usage.getData().get(914).getLabel());
        Assert.assertEquals("2015-06-19T15:15:00.000Z", usage.getData().get(915).getLabel());
        Assert.assertEquals(14L, usage.getData().get(913).getCount());
        Assert.assertEquals(15L, usage.getData().get(914).getCount());
        Assert.assertEquals(17L, usage.getData().get(915).getCount());

    }