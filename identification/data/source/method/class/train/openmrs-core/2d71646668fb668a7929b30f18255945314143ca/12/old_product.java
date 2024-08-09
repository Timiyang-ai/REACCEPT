public void handle(OpenmrsObject openmrsObject, User creator, Date dateCreated, String reason) {
		if (openmrsObject.getUuid() == null)
			openmrsObject.setUuid(UUID.randomUUID().toString());
	}