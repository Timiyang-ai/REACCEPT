public Symbol normalizeInputForReference(Symbol valueSymbol, Reference reference, TableInfo tableInfo) {
        assert valueSymbol != null : "valueSymbol must not be null";

        DataType<?> targetType = getTargetType(valueSymbol, reference);
        if (!(valueSymbol instanceof Literal)) {
            return ExpressionAnalyzer.castIfNeededOrFail(valueSymbol, targetType);
        }
        Literal literal = (Literal) valueSymbol;
        try {
            literal = Literal.convert(literal, reference.valueType());
        } catch (ConversionException e) {
            throw new ColumnValidationException(
                reference.ident().name(),
                tableInfo.ident(),
                String.format(Locale.ENGLISH, "%s cannot be cast to type %s", SymbolPrinter.INSTANCE.printSimple(valueSymbol),
                    reference.valueType().getName()));
        }
        Object value = literal.value();
        if (value == null) {
            return literal;
        }
        try {
            if (targetType == DataTypes.OBJECT) {
                //noinspection unchecked
                normalizeObjectValue((Map) value, reference, tableInfo);
            } else if (isObjectArray(targetType)) {
                normalizeObjectArrayValue((Object[]) value, reference, tableInfo);
            }
        } catch (ConversionException e) {
            throw new ColumnValidationException(
                reference.ident().name(),
                tableInfo.ident(),
                SymbolFormatter.format(
                    "\"%s\" has a type that can't be implicitly cast to that of \"%s\" (" +
                    reference.valueType().getName() + ")",
                    literal,
                    reference
                ));
        }
        return literal;
    }