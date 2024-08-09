    @Test
    public void sumDouble_noData() {
        Query<TestEntity> baseQuery = box.query().build();
        // Integer.
        assertEquals(0, baseQuery.property(simpleByte).sumDouble(), 0.0001);
        assertEquals(0, baseQuery.property(simpleInt).sumDouble(), 0.0001);
        assertEquals(0, baseQuery.property(simpleShort).sumDouble(), 0.0001);
        assertEquals(0, baseQuery.property(simpleLong).sumDouble(), 0.0001);
        // Integer treated as unsigned.
        assertEquals(0, baseQuery.property(simpleIntU).sumDouble(), 0.0001);
        assertEquals(0, baseQuery.property(simpleShortU).sumDouble(), 0.0001);
        assertEquals(0, baseQuery.property(simpleLongU).sumDouble(), 0.0001);
        // Floating point.
        assertEquals(0, baseQuery.property(simpleFloat).sumDouble(), 0.0001);
        assertEquals(0, baseQuery.property(simpleDouble).sumDouble(), 0.0001);
    }