public Symbol normalizeInputForReference(
            Symbol valueSymbol, Reference reference, ExpressionAnalysisContext context) {

        Literal literal;
        try {
            valueSymbol = normalizer.normalize(valueSymbol);
            assert valueSymbol != null : "valueSymbol must not be null";
            if (valueSymbol.symbolType() != SymbolType.LITERAL) {
                DataType targetType = reference.valueType();
                if (reference instanceof DynamicReference) {
                    targetType = valueSymbol.valueType();
                }
                return ExpressionAnalyzer.castIfNeededOrFail(valueSymbol, targetType, context);
            }
            literal = (Literal) valueSymbol;

            if (reference instanceof DynamicReference) {
                DataType<?> dataType = literal.valueType();
                if (reference.info().columnPolicy() != ColumnPolicy.IGNORED) {
                    raiseIfNestedArray(dataType, reference.info().ident().columnIdent());
                }
                ((DynamicReference) reference).valueType(dataType);
            } else {
                raiseIfNestedArray(literal.valueType(), reference.info().ident().columnIdent());
                literal = Literal.convert(literal, reference.valueType());
            }
        } catch (ConversionException e) {
            throw new ColumnValidationException(
                    reference.info().ident().columnIdent().name(),
                    String.format("%s can not be cast to \'%s\'", SymbolFormatter.INSTANCE.formatSimple(valueSymbol),
                            reference.valueType().getName()));
        }

        try {
            // 3. if reference is of type object - do special validation
            if (reference.info().type() == DataTypes.OBJECT) {
                @SuppressWarnings("unchecked")
                Map<String, Object> value = (Map<String, Object>) literal.value();
                if (value == null) {
                    return Literal.NULL;
                }
                literal = Literal.newLiteral(normalizeObjectValue(value, reference.info(), true));
            } else if (isObjectArray(reference.info().type())) {
                Object[] value = (Object[]) literal.value();
                if (value == null) {
                    return Literal.NULL;
                }
                literal = Literal.newLiteral(
                        reference.info().type(),
                        normalizeObjectArrayValue(value, reference.info(), true)
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