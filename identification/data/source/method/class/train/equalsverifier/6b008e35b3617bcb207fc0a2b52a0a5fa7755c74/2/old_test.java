    @Test
    public void giveTuple() {
        Tuple<Point> actual = pv.giveTuple(POINT_TAG);
        assertEquals(Tuple.of(new Point(42, 42), new Point(1337, 1337), new Point(42, 42)), actual);
    }