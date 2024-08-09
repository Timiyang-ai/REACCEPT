public void handle(OpenmrsObject openmrsObject, User creator, Date dateCreated, String reason) {
		if (openmrsObject.getUuid() == null)
			openmrsObject.setUuid(UUID.randomUUID().toString());
		
		//Set all empty string properties to null
		List<Field> fields = Reflect.getAllFields(openmrsObject.getClass());
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			
			if (!field.getType().getSimpleName().equals("String")) {
				continue;
			}
			
			try {
				Object value = PropertyUtils.getProperty(openmrsObject, field.getName());
				if (value != null && value.toString().isEmpty()) {
					PropertyUtils.setProperty(openmrsObject, field.getName(), null);
				}
			}
			catch (Exception ex) {
				log.error("Failed to set property value for " + field.getName() + " to NULL", ex);
			}
		}
	}