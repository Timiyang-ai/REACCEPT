@Test
    public void testFindDomainBounds() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        List visibleSeriesKeys = new java.util.ArrayList();
        Range r = DatasetUtils.findDomainBounds(dataset, visibleSeriesKeys,
                true);
        assertNull(r);

        TimeSeries s1 = new TimeSeries("S1");
        dataset.addSeries(s1);
        visibleSeriesKeys.add("S1");
        r = DatasetUtils.findDomainBounds(dataset, visibleSeriesKeys, true);
        assertNull(r);

        // store the current time zone
        TimeZone saved = TimeZone.getDefault();
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));

        s1.add(new Year(2008), 8.0);
        r = DatasetUtils.findDomainBounds(dataset, visibleSeriesKeys, true);
        assertEquals(1199142000000.0, r.getLowerBound(), EPSILON);
        assertEquals(1230764399999.0, r.getUpperBound(), EPSILON);

        TimeSeries s2 = new TimeSeries("S2");
        dataset.addSeries(s2);
        s2.add(new Year(2009), 9.0);
        s2.add(new Year(2010), 10.0);
        r = DatasetUtils.findDomainBounds(dataset, visibleSeriesKeys, true);
        assertEquals(1199142000000.0, r.getLowerBound(), EPSILON);
        assertEquals(1230764399999.0, r.getUpperBound(), EPSILON);

        visibleSeriesKeys.add("S2");
        r = DatasetUtils.findDomainBounds(dataset, visibleSeriesKeys, true);
        assertEquals(1199142000000.0, r.getLowerBound(), EPSILON);
        assertEquals(1293836399999.0, r.getUpperBound(), EPSILON);

        // restore the default time zone
        TimeZone.setDefault(saved);
    }