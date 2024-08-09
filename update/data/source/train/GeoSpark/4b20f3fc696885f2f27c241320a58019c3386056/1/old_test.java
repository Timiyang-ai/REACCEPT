@Test
    public void testContains() throws Exception {
        Circle circle = new Circle(0.0, 0.0, 0.1);
        GeometryFactory geometryFactory = new GeometryFactory();
        assertEquals(true, circle.contains(geometryFactory.createPoint(new Coordinate(0.0, 0.0))));
    }