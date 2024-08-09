public void handle(OpenmrsObject openmrsObject, User creator, Date dateCreated, String reason) {
		if (openmrsObject.getUuid() == null)
			openmrsObject.setUuid(UUID.randomUUID().toString());
		
		//Set all empty string properties, that do not have the AllowEmptyStrings annotation, to null.
		//And also trim leading and trailing white space for properties that do not have the
		//AllowLeadingOrTrailingWhitespace annotation.
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
				
				if ("".equals(value)) {
					//Set to null only if object is not already voided
					if (!(openmrsObject instanceof Voidable && ((Voidable) openmrsObject).isVoided())) {
						PropertyUtils.setProperty(openmrsObject, property.getName(), null);
					}
				}
			}
			catch (InvocationTargetException ex) {
				if (log.isWarnEnabled())
					log.warn("Failed to access property " + property.getName() + "; accessor threw exception.", ex);
			}
			catch (Exception ex) {
				throw new APIException(
				        "Failed to change property value from empty string to null for " + property.getName(), ex);
			}
		}
	}