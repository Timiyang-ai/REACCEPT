public void handle(OpenmrsObject openmrsObject, User creator, Date dateCreated, String reason) {
		if (openmrsObject.getUuid() == null)
			openmrsObject.setUuid(UUID.randomUUID().toString());
		
		//Set all empty string properties to null
		PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(openmrsObject);
		for (PropertyDescriptor property : properties) {
			
			if (property.getPropertyType() == null) {
				continue;
			}
			
			//For instance Patient has no setter methods for familyName, middleName and givenName
			//yet it has getters for these properties and is why we loop through them.
			if (property.getWriteMethod() == null) {
				continue;
			}
			
			if (!property.getPropertyType().equals(String.class)) {
				continue;
			}
			
			if (property.getWriteMethod().getAnnotation(AllowEmptyStrings.class) != null) {
				continue;
			}
			
			try {
				Object value = PropertyUtils.getProperty(openmrsObject, property.getName());
				if (value == null) {
					continue;
				}
				
				if (property.getWriteMethod().getAnnotation(AllowLeadingOrTrailingWhitespace.class) == null) {
					value = ((String) value).trim();
				}
				
				if ("".equals(value)) {
					
					//Set to null only if object is not already voided
					if (!(openmrsObject instanceof Voidable && ((Voidable) openmrsObject).isVoided())) {
						PropertyUtils.setProperty(openmrsObject, property.getName(), null);
					}
				}
			}
			/*catch (InvocationTargetException ex) {
				if (ex.getTargetException() instanceof TransientObjectException) {
					//The FormServiceTest throws this for the "xslt" and "template" properties, with this error message:
					//org.hibernate.TransientObjectException: object references an unsaved transient 
					//instance - save the transient instance before flushing: org.openmrs.Form
					log.error("Failed to change property value from empty string to null for " + property.getName(), ex);
				} else {
					throw new APIException("Failed to change property value from empty string to null for "
					        + property.getName(), ex);
				}
			}*/
			catch (Exception ex) {
				throw new APIException(
				        "Failed to change property value from empty string to null for " + property.getName(), ex);
			}
		}
	}