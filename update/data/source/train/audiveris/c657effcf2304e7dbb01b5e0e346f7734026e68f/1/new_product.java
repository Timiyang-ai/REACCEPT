static Symbol parseSymbol(JSONArray jsonSymbol) {
        String symbolId = jsonSymbol.getString(4);
        Symbol symbol = SymbolFactory.produce(symbolId);
        if (symbol == null) {
            return null;
        }
        symbol.assignCoordinates(
                jsonSymbol.getInt(0),
                jsonSymbol.getInt(1),
                jsonSymbol.getInt(2),
                jsonSymbol.getInt(3)
        );
        return symbol;
    }