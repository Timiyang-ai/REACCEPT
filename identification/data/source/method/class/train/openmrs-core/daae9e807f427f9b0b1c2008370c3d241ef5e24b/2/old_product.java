@Transactional(readOnly = true)
	public List<String> getConceptStopWords(Locale locale);