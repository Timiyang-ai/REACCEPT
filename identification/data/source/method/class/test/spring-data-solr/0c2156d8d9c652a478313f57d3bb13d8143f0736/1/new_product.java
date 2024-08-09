@Override
	public <T> T getById(Serializable id, Class<T> clazz) {
		return getById(getSolrCoreOrBeanCollection(clazz), id, clazz);
	}