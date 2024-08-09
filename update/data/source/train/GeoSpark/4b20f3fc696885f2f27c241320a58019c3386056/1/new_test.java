@Test
    public void testContains() throws Exception {
        Circle circle = new Circle(geomFact.createPoint(new Coordinate(0.0,0.0)), 0.1);
        GeometryFactory geometryFactory = new GeometryFactory();
        assertEquals(true, circle.covers(geometryFactory.createPoint(new Coordinate(0.0, 0.0))));
    }