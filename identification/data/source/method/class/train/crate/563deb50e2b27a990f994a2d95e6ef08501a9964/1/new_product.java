public static Symbol normalizeInputForReference(Symbol valueSymbol, Reference reference, TableInfo tableInfo) {
        assert valueSymbol != null : "valueSymbol must not be null";

        DataType<?> targetType = getTargetType(valueSymbol, reference);
        if (!(valueSymbol instanceof Literal)) {
            return ExpressionAnalyzer.cast(valueSymbol, targetType);
        }
        Literal literal = (Literal) valueSymbol;
        try {
            literal = Literal.convert(literal, reference.valueType());
        } catch (ConversionException e) {
            throw new ColumnValidationException(
                reference.column().name(),
                tableInfo.ident(),
                String.format(Locale.ENGLISH, "Cannot cast %s to type %s", SymbolPrinter.INSTANCE.printUnqualified(valueSymbol),
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
                reference.column().name(),
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