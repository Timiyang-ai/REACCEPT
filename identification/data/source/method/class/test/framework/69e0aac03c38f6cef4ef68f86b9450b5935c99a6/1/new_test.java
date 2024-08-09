    @Test
    public void intersectsTest() {
        assertTrue("[0..10[ intersects [5..15[",
                Range.between(0, 10).intersects(Range.between(5, 15)));
        assertTrue("[0..10[ does not intersect [10..20[",
                !Range.between(0, 10).intersects(Range.between(10, 20)));
    }