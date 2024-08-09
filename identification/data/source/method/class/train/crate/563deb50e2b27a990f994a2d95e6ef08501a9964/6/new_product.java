public Symbol normalizeInputForReference(
            Symbol valueSymbol, Reference reference, ExpressionAnalysisContext context) {

        valueSymbol = normalizer.normalize(valueSymbol);
        assert valueSymbol != null : "valueSymbol must not be null";

        DataType<?> targetType = getTargetType(valueSymbol, reference);
        if (!(valueSymbol instanceof Literal)) {
            return ExpressionAnalyzer.castIfNeededOrFail(valueSymbol, targetType, context);
        }
        Literal literal = (Literal) valueSymbol;
        raiseIfNestedArray(literal.valueType(), reference.info().ident().columnIdent());
        try {
            literal = Literal.convert(literal, reference.valueType());
        } catch (ConversionException e) {
            throw new ColumnValidationException(
                    reference.info().ident().columnIdent().name(),
                    String.format("%s can not be cast to \'%s\'", SymbolFormatter.INSTANCE.formatSimple(valueSymbol),
                            reference.valueType().getName()));
        }
        Object value = literal.value();
        if (value == null) {
            return literal;
        }
        try {
            if (targetType == DataTypes.OBJECT) {
                //noinspection unchecked
                literal = Literal.newLiteral(normalizeObjectValue((Map) value, reference.info(), true));
            } else if (isObjectArray(targetType)) {
                literal = Literal.newLiteral(
                        reference.info().type(),
                        normalizeObjectArrayValue((Object[]) value, reference.info(), true)
                );
            }
        } catch (ConversionException e) {
            throw new ColumnValidationException(
                    reference.info().ident().columnIdent().name(),
                    SymbolFormatter.INSTANCE.formatTmpl(
                            "\"%s\" has a type that can't be implicitly cast to that of \"%s\" (" + reference.valueType().getName() + ")",
                            literal,
                            reference
                    ));
        }
        return literal;
    }