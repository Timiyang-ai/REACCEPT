@Test
    public void testIntersects() throws Exception {
        Circle circle = new Circle(geomFact.createPoint(new Coordinate(0.0,0.0)), 0.1);
        Envelope envelope = new Envelope(-0.1, 0.1, -0.1, 0.1);
        assertEquals(true, circle.getEnvelopeInternal().covers(envelope));

        circle = new Circle(geomFact.createPoint(new Coordinate(-0.1,0.0)), 0.1);
        envelope = new Envelope(-0.1, 0.1, -0.1, 0.1);
        assertEquals(true, circle.getEnvelopeInternal().intersects(envelope));

        circle = new Circle(geomFact.createPoint(new Coordinate(-0.3,0.0)), 0.1);
        envelope = new Envelope(-0.1, 0.1, -0.1, 0.1);
        assertEquals(false, circle.getEnvelopeInternal().intersects(envelope));
    }