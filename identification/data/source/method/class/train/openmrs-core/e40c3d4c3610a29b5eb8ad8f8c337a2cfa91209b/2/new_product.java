@Override
	public boolean onSave(Object entity, Serializable id, Object[] entityCurrentState, String[] propertyNames, Type[] types) {
		boolean objectWasChanged;
		boolean personObjectWasChanged = false;
		
		if (entity instanceof Person) {
			personObjectWasChanged = setPersonCreatorAndDateCreatedIfNull(entity, entityCurrentState, propertyNames);
		}
		objectWasChanged = setCreatorAndDateCreatedIfNull(entity, entityCurrentState, propertyNames);
		return objectWasChanged || personObjectWasChanged;
	}