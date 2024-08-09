@Override
	public <T> T getById(Serializable id, Class<T> clazz) {

		Assert.notNull(id, "Id must not be 'null'.");

		Collection<T> result = getById(Collections.singletonList(id), clazz);
		if (result.isEmpty()) {
			return null;
		}
		return result.iterator().next();
	}