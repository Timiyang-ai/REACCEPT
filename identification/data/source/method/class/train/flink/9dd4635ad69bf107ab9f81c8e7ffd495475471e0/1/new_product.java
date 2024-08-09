public static <T extends Value> Class<FieldParser<T>> getParserForType(Class<T> type) {
		Class<? extends FieldParser<?>> parser = PARSERS.get(type);
		if (parser == null) {
			return null;
		} else {
			@SuppressWarnings("unchecked")
			Class<FieldParser<T>> typedParser = (Class<FieldParser<T>>) parser;
			return typedParser;
		}
	}