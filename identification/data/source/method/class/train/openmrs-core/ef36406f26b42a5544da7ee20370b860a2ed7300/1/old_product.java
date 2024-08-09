@Override
	public Long getCountOfProviders(String name, boolean includeRetired) {
		Criteria criteria = prepareProviderCriteria(name, includeRetired);
		criteria.setProjection(Projections.countDistinct("providerId"));
		return (Long) criteria.uniqueResult();
	}