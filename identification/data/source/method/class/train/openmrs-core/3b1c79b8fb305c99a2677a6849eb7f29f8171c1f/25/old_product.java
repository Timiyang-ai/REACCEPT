@Transactional(readOnly = true)
	public List<GlobalProperty> getGlobalPropertiesByPrefix(String prefix);