@Test
    public void testAddAsEqual() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<String>(topCities);
        comparator.addAsEqual("New York", "Minneapolis");
        assertEquals(0, comparator.compare("New York", "Minneapolis"));
        assertEquals(-1, comparator.compare("Tokyo", "Minneapolis"));
        assertEquals(1, comparator.compare("Shanghai", "Minneapolis"));
    }