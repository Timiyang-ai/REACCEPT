@Test
    public void testGenerateHistogramSkeleton() throws Exception {
        DateTime from = parseDate("2015-01-01T00:00:00Z");
        DateTime to = parseDate("2015-01-10T00:00:00Z");
        UsageHistogramBean histogram = new UsageHistogramBean();
        Map<String, UsageDataPoint> index = ESMetricsAccessor.generateHistogramSkeleton(histogram, from, to, UsageHistogramIntervalType.day);
        Assert.assertEquals(9, index.size());
        Assert.assertEquals(9, histogram.getData().size());
        Assert.assertEquals("2015-01-01T00:00:00.000Z", histogram.getData().get(0).getLabel());
        Assert.assertEquals("2015-01-03T00:00:00.000Z", histogram.getData().get(2).getLabel());
        Assert.assertEquals("2015-01-07T00:00:00.000Z", histogram.getData().get(6).getLabel());


        from = parseDate("2015-01-01T00:00:00Z");
        to = parseDate("2015-01-03T00:00:00Z");
        histogram = new UsageHistogramBean();
        index = ESMetricsAccessor.generateHistogramSkeleton(histogram, from, to, UsageHistogramIntervalType.hour);
        Assert.assertEquals(48, index.size());
        Assert.assertEquals(48, histogram.getData().size());
        Assert.assertEquals("2015-01-01T00:00:00.000Z", histogram.getData().get(0).getLabel());
        Assert.assertEquals("2015-01-01T02:00:00.000Z", histogram.getData().get(2).getLabel());
        Assert.assertEquals("2015-01-01T06:00:00.000Z", histogram.getData().get(6).getLabel());
        Assert.assertEquals("2015-01-02T18:00:00.000Z", histogram.getData().get(42).getLabel());


        from = parseDate("2015-01-01");
        to = parseDate("2015-01-03");
        histogram = new UsageHistogramBean();
        index = ESMetricsAccessor.generateHistogramSkeleton(histogram, from, to, UsageHistogramIntervalType.hour);
        Assert.assertEquals(48, index.size());
        Assert.assertEquals(48, histogram.getData().size());
        Assert.assertEquals("2015-01-01T00:00:00.000Z", histogram.getData().get(0).getLabel());
        Assert.assertEquals("2015-01-01T02:00:00.000Z", histogram.getData().get(2).getLabel());
        Assert.assertEquals("2015-01-01T06:00:00.000Z", histogram.getData().get(6).getLabel());
        Assert.assertEquals("2015-01-02T18:00:00.000Z", histogram.getData().get(42).getLabel());


        from = parseDate("2015-01-01T00:00:00Z");
        to = parseDate("2015-01-02T00:00:00Z");
        histogram = new UsageHistogramBean();
        index = ESMetricsAccessor.generateHistogramSkeleton(histogram, from, to, UsageHistogramIntervalType.minute);
        Assert.assertEquals(1440, index.size());
        Assert.assertEquals(1440, histogram.getData().size());
        Assert.assertEquals("2015-01-01T00:00:00.000Z", histogram.getData().get(0).getLabel());
        Assert.assertEquals("2015-01-01T00:20:00.000Z", histogram.getData().get(20).getLabel());
        Assert.assertEquals("2015-01-01T00:30:00.000Z", histogram.getData().get(30).getLabel());


        from = parseDate("2015-01-01");
        to = parseDate("2015-12-31");
        histogram = new UsageHistogramBean();
        index = ESMetricsAccessor.generateHistogramSkeleton(histogram, from, to, UsageHistogramIntervalType.month);
        Assert.assertEquals(12, index.size());
        Assert.assertEquals(12, histogram.getData().size());
        Assert.assertEquals("2015-01-01T00:00:00.000Z", histogram.getData().get(0).getLabel());
        Assert.assertEquals("2015-06-01T00:00:00.000Z", histogram.getData().get(5).getLabel());

        System.out.println("--------------------------------");

        from = parseDate("2015-01-01");
        to = parseDate("2015-12-30");
        histogram = new UsageHistogramBean();
        index = ESMetricsAccessor.generateHistogramSkeleton(histogram, from, to, UsageHistogramIntervalType.week);
        Assert.assertEquals(53, index.size());
        Assert.assertEquals(53, histogram.getData().size());
        Assert.assertEquals("2014-12-28T00:00:00.000Z", histogram.getData().get(0).getLabel());
        Assert.assertEquals("2015-02-01T00:00:00.000Z", histogram.getData().get(5).getLabel());
        Assert.assertEquals("2015-04-12T00:00:00.000Z", histogram.getData().get(15).getLabel());
    }