@Override
	public boolean onSave(Object entity, Serializable id, Object[] entityCurrentState, String[] propertyNames, Type[] types) {
		return setCreatorAndDateCreatedIfNull(entity, entityCurrentState, propertyNames);
	}