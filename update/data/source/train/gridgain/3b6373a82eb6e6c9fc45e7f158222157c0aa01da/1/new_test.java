@Test
    public void testToJson() {
        HistogramMetric singleBucket = new HistogramMetric(new long[] { 1 });

        singleBucket.value(1);

        String singleBucketJson = toJson(singleBucket);

        assertEquals("{\"bounds\":[1],\"values\":" +
                "[{\"fromInclusive\":0,\"toInclusive\":1,\"value\":1},{\"fromExclusive\":1,\"value\":0}]}",
            singleBucketJson
        );

        HistogramMetric histo = new HistogramMetric(new long[] { 1, 5, 10, 20, 50 });

        histo.value(1);
        histo.value(12);
        histo.value(13);
        histo.value(35);
        histo.value(20);
        histo.value(100);

        String histoJson = toJson(histo);

        assertEquals("{\"bounds\":[1,5,10,20,50],\"values\":" +
                "[{\"fromInclusive\":0,\"toInclusive\":1,\"value\":1}," +
                "{\"fromExclusive\":1,\"toInclusive\":5,\"value\":0}," +
                "{\"fromExclusive\":5,\"toInclusive\":10,\"value\":0}," +
                "{\"fromExclusive\":10,\"toInclusive\":20,\"value\":3}," +
                "{\"fromExclusive\":20,\"toInclusive\":50,\"value\":1}," +
                "{\"fromExclusive\":50,\"value\":1}]}",
            histoJson
        );
    }