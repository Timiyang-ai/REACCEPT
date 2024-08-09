    private Symbol normalizeInputForReference(Symbol valueSymbol, Reference reference) {
        return ValueNormalizer.normalizeInputForReference(valueSymbol, reference, userTableInfo);
    }