@Override
	public <T> Collection<T> getById(final Collection<? extends Serializable> ids, final Class<T> clazz) {
		return getById(getSolrCoreOrBeanCollection(clazz), ids, clazz);
	}