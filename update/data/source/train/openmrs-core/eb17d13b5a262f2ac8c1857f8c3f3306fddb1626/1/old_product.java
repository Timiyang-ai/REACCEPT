@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
	        String[] propertyNames, Type[] types) throws CallbackException {
		
		if (entity instanceof Auditable) {
			if (log.isDebugEnabled())
				log.debug("Setting changed by fields on " + entity);
			
			// loop over the properties and only change the changedBy and dateChanged fields
			for (int x = 0; x < propertyNames.length; x++) {
				if (propertyNames[x].equals("changedBy"))
					currentState[x] = Context.getAuthenticatedUser();
				else if (propertyNames[x].equals("dateChanged"))
					currentState[x] = new Date();
			}
			
			// tell hibernate that we've changed this object
			return true;
		}
		
		// if we get here it means we didn't change anything
		return false;
	}