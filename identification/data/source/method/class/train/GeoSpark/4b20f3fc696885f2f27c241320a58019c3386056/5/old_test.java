@Test
    public void testGetCenter() throws Exception {
        Circle circle = new Circle(0.0, 0.0, 0.1);
        assertEquals(circle.getCenter().getX(), 0.0, 0.01);
    }