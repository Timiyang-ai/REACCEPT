public Symbol normalizeInputForReference(Symbol valueSymbol, Reference reference) {

        valueSymbol = normalizer.normalize(valueSymbol);
        assert valueSymbol != null : "valueSymbol must not be null";

        DataType<?> targetType = getTargetType(valueSymbol, reference);
        if (!(valueSymbol instanceof Literal)) {
            return ExpressionAnalyzer.castIfNeededOrFail(valueSymbol, targetType);
        }
        Literal literal = (Literal) valueSymbol;
        raiseIfNestedArray(literal.valueType(), reference.info().ident().columnIdent());
        try {
            literal = Literal.convert(literal, reference.valueType());
        } catch (ConversionException e) {
            throw new ColumnValidationException(
                    reference.info().ident().columnIdent().name(),
                    String.format("%s cannot be cast to type %s", SymbolPrinter.INSTANCE.printSimple(valueSymbol),
                            reference.valueType().getName()));
        }
        Object value = literal.value();
        if (value == null) {
            return literal;
        }
        try {
            if (targetType == DataTypes.OBJECT) {
                //noinspection unchecked
                normalizeObjectValue((Map) value, reference.info());
            } else if (isObjectArray(targetType)) {
                normalizeObjectArrayValue((Object[]) value, reference.info());
            }
        } catch (ConversionException e) {
            throw new ColumnValidationException(
                    reference.info().ident().columnIdent().name(),
                    SymbolFormatter.format(
                            "\"%s\" has a type that can't be implicitly cast to that of \"%s\" (" + reference.valueType().getName() + ")",
                            literal,
                            reference
                    ));
        }
        return literal;
    }