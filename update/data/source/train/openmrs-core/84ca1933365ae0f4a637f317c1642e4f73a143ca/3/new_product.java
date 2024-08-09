@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
	        String[] propertyNames, Type[] types) throws CallbackException {
		boolean objectWasChanged = false;
		
		objectWasChanged = setCreatorAndDateCreatedIfNull(entity, currentState, propertyNames);
		
		if (entity instanceof Auditable && propertyNames != null) {
			if (log.isDebugEnabled())
				log.debug("Setting changed by fields on " + entity);
			
			if (setValue(currentState, propertyNames, "changedBy", Context.getAuthenticatedUser(), false)) {
				objectWasChanged = true;
			}
			
			if (setValue(currentState, propertyNames, "dateChanged", new Date(), false)) {
				objectWasChanged = true;
			}
		}
		
		return objectWasChanged;
	}