@Test
    public void testGetMBR() throws Exception {
        Circle circle = new Circle(0.0, 0.0, 0.1);

        assertEquals(circle.getMBR().getMinX(), circle.getCenter().getX() - circle.getRadius(), 0.01);
    }