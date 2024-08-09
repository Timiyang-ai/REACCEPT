@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
	        String[] propertyNames, Type[] types) throws CallbackException {
		
		if (entity instanceof Auditable && propertyNames != null) {
			if (log.isDebugEnabled())
				log.debug("Setting changed by fields on " + entity.getClass());
			
			// the return value
			boolean objectWasChanged = false;
			
			// loop over the properties and only change the changedBy and dateChanged fields
			Date currentDate = new Date();
			
			User authenticatedUser = Context.getAuthenticatedUser();
			for (int x = 0; x < propertyNames.length; x++) {
				if (propertyNames[x].equals("changedBy") && previousState != null && previousState[x] != authenticatedUser) {
					currentState[x] = authenticatedUser;
					objectWasChanged = true;
				} else if (propertyNames[x].equals("dateChanged") && previousState != null
				        && previousState[x] != currentDate) {
					currentState[x] = currentDate;
					objectWasChanged = true;
				}
			}
			
			// tell hibernate that we've changed this object
			return objectWasChanged;
		}
		
		// if we get here it means we didn't change anything
		return false;
	}