public void handle(OpenmrsObject openmrsObject, User creator, Date dateCreated, String reason) {
		if (openmrsObject.getUuid() == null) {
			openmrsObject.setUuid(UUID.randomUUID().toString());
		}
		
		//Set all empty string properties, that do not have the AllowEmptyStrings annotation, to null.
		//And also trim leading and trailing white space for properties that do not have the
		//AllowLeadingOrTrailingWhitespace annotation.
		PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(openmrsObject);
		for (PropertyDescriptor property : properties) {
			
			if (property.getPropertyType() == null) {
				continue;
			}
			
			// Ignore properties that don't have a getter (e.g. GlobalProperty.valueReferenceInternal) or
			// don't have a setter (e.g. Patient.familyName)
			if (property.getWriteMethod() == null || property.getReadMethod() == null) {
				continue;
			}
			
			// Ignore properties that have a deprecated getter or setter
			if (property.getWriteMethod().getAnnotation(Deprecated.class) != null
			        || property.getReadMethod().getAnnotation(Deprecated.class) != null) {
				continue;
			}
			
			//We are dealing with only strings
			if (!property.getPropertyType().equals(String.class)) {
				continue;
			}
			
			try {
				Object value = PropertyUtils.getProperty(openmrsObject, property.getName());
				if (value == null) {
					continue;
				}
				
				Object valueBeforeTrim = value;
				if (property.getWriteMethod().getAnnotation(AllowLeadingOrTrailingWhitespace.class) == null) {
					value = ((String) value).trim();
					
					//If we have actually trimmed any space, set the trimmed value.
					if (!valueBeforeTrim.equals(value)) {
						PropertyUtils.setProperty(openmrsObject, property.getName(), value);
					}
				}
				
				//Check if user is interested in setting empty strings to null
				if (property.getWriteMethod().getAnnotation(AllowEmptyStrings.class) != null) {
					continue;
				}
				
				if ("".equals(value) && !(openmrsObject instanceof Voidable && ((Voidable) openmrsObject).isVoided())) {
					//Set to null only if object is not already voided
					PropertyUtils.setProperty(openmrsObject, property.getName(), null);
				}
			}
			catch (UnsupportedOperationException ex) {
				// there is no need to log this. These should be (mostly) silently skipped over 
				if (log.isInfoEnabled()) {
					log.info("The property " + property.getName() + " is no longer supported and should be ignored.", ex);
				}
			}
			catch (InvocationTargetException ex) {
				if (log.isWarnEnabled()) {
					log.warn("Failed to access property " + property.getName() + "; accessor threw exception.", ex);
				}
			}
			catch (Exception ex) {
				throw new APIException("failed.change.property.value", new Object[] { property.getName() }, ex);
			}
		}
	}