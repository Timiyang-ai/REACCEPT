@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.MANAGE_CONCEPT_STOP_WORDS)
	public List<String> getConceptStopWords(Locale locale);