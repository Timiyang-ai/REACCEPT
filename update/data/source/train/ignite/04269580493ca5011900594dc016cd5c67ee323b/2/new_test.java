@Test
    public void testRemove() throws Exception {
        MetricRegistry mreg = new MetricRegistry("group", null);

        LongMetricImpl cntr = mreg.metric("my.name", null);
        LongMetricImpl cntr2 = mreg.metric("my.name.x", null);

        assertNotNull(cntr);
        assertNotNull(cntr2);

        assertNotNull(mreg.findMetric("my.name"));
        assertNotNull(mreg.findMetric("my.name.x"));

        mreg.remove("my.name");

        assertNull(mreg.findMetric("my.name"));
        assertNotNull(mreg.findMetric("my.name.x"));

        cntr = mreg.metric("my.name", null);

        assertNotNull(mreg.findMetric("my.name"));
    }