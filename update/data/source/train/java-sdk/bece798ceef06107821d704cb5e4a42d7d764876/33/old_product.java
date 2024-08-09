public TranslationResult translate(final String text, final String source, final String target) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(TEXT, text);
		params.put(SOURCE, source);
		params.put(TARGET, target);
		return translate(params);
	}