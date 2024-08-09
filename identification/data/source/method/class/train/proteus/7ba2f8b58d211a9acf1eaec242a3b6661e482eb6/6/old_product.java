public Value evaluate(JsonElement data, int index) {
        Value empty = StringAttributeProcessor.EMPTY;
        Result result;
        if (expressions.length == 1) {
            result = expressions[0].evaluate(data, index);
            return result.isSuccess() ? Value.fromJson(result.element) : empty;
        } else {
            String[] variables = new String[expressions.length];
            for (int i = 0; i < expressions.length; i++) {
                result = expressions[i].evaluate(data, index);
                variables[i] = result.isSuccess() ? result.element.toString() : "";
            }
            return new Primitive(String.format(template, (Object[]) variables));
        }
    }