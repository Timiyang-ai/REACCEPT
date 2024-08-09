@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
	                            String[] propertyNames, Type[] types) {
		
		if (getSupportedType().isAssignableFrom(entity.getClass())) {
			List<String> changedProperties = null;
			for (int i = 0; i < propertyNames.length; i++) {
				String property = propertyNames[i];
				if (ArrayUtils.contains(getMutablePropertyNames(), property)) {
					continue;
				}
				
				boolean isVoidedOrRetired = false;
				if (Voidable.class.isAssignableFrom(entity.getClass())) {
					isVoidedOrRetired = ((Voidable) entity).getVoided();
				} else if (Retireable.class.isAssignableFrom(entity.getClass())) {
					isVoidedOrRetired = ((Retireable) entity).getRetired();
				}
				if (isVoidedOrRetired && ignoreVoidedOrRetiredObjects()) {
					continue;
				}
				
				Object previousValue = (previousState != null) ? previousState[i] : null;
				Object currentValue = (currentState != null) ? currentState[i] : null;
				if (!OpenmrsUtil.nullSafeEquals(currentValue, previousValue)) {
					if (changedProperties == null) {
						changedProperties = new ArrayList<String>();
					}
					changedProperties.add(property);
				}
			}
			if (CollectionUtils.isNotEmpty(changedProperties)) {
				if (log.isDebugEnabled()) {
					log.debug("The following fields cannot be changed for " + getSupportedType() + ":" + changedProperties);
				}
				throw new APIException("Editing some fields " + changedProperties + " on " + getSupportedType().getSimpleName() + " is not allowed");
			}
		}
		
		return false;
	}