@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
	        String[] propertyNames, Type[] types) throws CallbackException {
		boolean objectWasChanged;
		
		objectWasChanged = setCreatorAndDateCreatedIfNull(entity, currentState, propertyNames);
		
		if (entity instanceof Auditable && propertyNames != null) {
			if (log.isDebugEnabled())
				log.debug("Setting changed by fields on " + entity.getClass());
			
			HashMap<String, Object> propertyValues = getPropertyValuesToUpdate();
			objectWasChanged = changeProperties(currentState, propertyNames, objectWasChanged, propertyValues, false);
		}
		return objectWasChanged;
	}