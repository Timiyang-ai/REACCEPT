@Test
    public void testSetRadius() throws Exception {
        Circle circle = new Circle(geomFact.createPoint(new Coordinate(0.0,0.0)), 0.1);
        circle.setRadius(0.2);
        assertEquals(circle.getRadius(), 0.2, 0.01);
    }