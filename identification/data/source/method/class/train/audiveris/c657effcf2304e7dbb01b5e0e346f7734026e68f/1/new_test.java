@Test
    public void parseSample() {
        String json = "[1001, 159, 1005, 163, \"articStaccatoBelow\"]";
        JSONArray array = new JSONArray(json);
        Symbol symbol = DataParser.parseSymbol(array);
        assertNotNull(symbol);
        assertEquals(1001, symbol.getFrom().x);
        assertEquals(159, symbol.getFrom().y);
        assertEquals(1005, symbol.getTo().x);
        assertEquals(163, symbol.getTo().y);
        assertEquals("articStaccatoBelow", symbol.getSymbolId());
    }