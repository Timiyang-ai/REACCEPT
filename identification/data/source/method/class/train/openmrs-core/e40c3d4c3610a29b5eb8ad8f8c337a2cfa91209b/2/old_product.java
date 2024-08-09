@Override
	public boolean onSave(Object entity, Serializable id, Object[] currentState, String[] propertyNames, Type[] types) {
		return setCreatorAndDateCreatedIfNull(entity, currentState, propertyNames);
	}