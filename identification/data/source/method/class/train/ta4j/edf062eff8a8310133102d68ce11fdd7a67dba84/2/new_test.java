    @Test
    public void toDouble() {
        BarSeries series = new BaseBarSeries();
        List<Num> values = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            values.add(series.numOf(i));
        }
        Indicator<Num> indicator = new MockIndicator(series, values);

        int index = 10;
        int barCount = 3;

        Double[] doubles = Indicator.toDouble(indicator, index, barCount);

        assertTrue(doubles.length == barCount);
        assertTrue(doubles[0] == 8d);
        assertTrue(doubles[1] == 9d);
        assertTrue(doubles[2] == 10d);
    }