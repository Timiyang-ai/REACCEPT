@Override
	public Long getCountOfProviders(String name, boolean includeRetired) {
	  Criteria criteria = prepareProviderCriteria(name, includeRetired);
	  return (long) criteria.list().size();
	}