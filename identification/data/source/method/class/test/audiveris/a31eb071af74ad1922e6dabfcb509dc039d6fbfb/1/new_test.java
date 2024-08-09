@Test
    public void assignCoordinates() {
        Symbol symbol = new Symbol(0, "test", "test");
        assertNull(symbol.getRectangle());
        symbol.assignCoordinates(1, 2, 3, 4);
        Rectangle rectangle = symbol.getRectangle();
        assertNotNull(rectangle);
        assertEquals(1, rectangle.x);
        assertEquals(2, rectangle.y);
        assertEquals(2, rectangle.width);
        assertEquals(2, rectangle.height);
    }