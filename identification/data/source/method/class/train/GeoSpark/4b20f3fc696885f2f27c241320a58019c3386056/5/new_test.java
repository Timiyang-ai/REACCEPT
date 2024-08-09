@Test
    public void testGetCenter() throws Exception {
    	
    	
        Circle circle = new Circle(geomFact.createPoint(new Coordinate(0.0,0.0)), 0.1);
        assertEquals(circle.getCenterPoint().x, 0.0, 0.01);
    }