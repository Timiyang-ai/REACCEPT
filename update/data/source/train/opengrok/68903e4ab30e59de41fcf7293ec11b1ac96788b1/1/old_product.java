public boolean hasDefinitionAt(String symbol, int lineNumber) {
        Set<Integer> lines = symbols.get(symbol);
        return lines != null && lines.contains(lineNumber);
    }