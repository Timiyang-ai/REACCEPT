@Test
    public void assignCoordinates() {
        Symbol symbol = new Symbol(0, "test", "test");
        assertNull(symbol.getFrom());
        assertNull(symbol.getTo());
        symbol.assignCoordinates(1, 2, 3, 4);
        assertNotNull(symbol.getFrom());
        assertNotNull(symbol.getTo());
        assertEquals(1, symbol.getFrom().x);
        assertEquals(2, symbol.getFrom().y);
        assertEquals(3, symbol.getTo().x);
        assertEquals(4, symbol.getTo().y);
    }