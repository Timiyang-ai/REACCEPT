public Value evaluate(JsonElement data, int index) {
        Value empty = StringAttributeProcessor.EMPTY;
        Result result;
        // the string object compare can be safely used here,
        // do not convert it to .equals()
        if (expressions.length == 1 && template == EMPTY_TEMPLATE) {
            result = expressions[0].evaluate(data, index);
            return result.isSuccess() ? Value.fromJson(result.element) : empty;
        } else {
            String[] variables = new String[expressions.length];
            String variable;
            for (int i = 0; i < expressions.length; i++) {
                result = expressions[i].evaluate(data, index);
                if (result.isSuccess()) {
                    if (result.element.isJsonPrimitive()) {
                        variable = result.element.getAsString();
                    } else {
                        variable = result.element.toString();
                    }
                } else {
                    variable = EMPTY_STRING;
                }
                variables[i] = variable;
            }
            return new Primitive(String.format(template, (Object[]) variables));
        }
    }