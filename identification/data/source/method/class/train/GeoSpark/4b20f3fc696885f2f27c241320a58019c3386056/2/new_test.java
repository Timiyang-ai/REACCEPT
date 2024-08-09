@Test
    public void testGetMBR() throws Exception {
        Circle circle = new Circle(geomFact.createPoint(new Coordinate(0.0,0.0)), 0.1);

        assertEquals(circle.getEnvelopeInternal().getMinX(), circle.getCenterPoint().x - circle.getRadius(), 0.01);
    }