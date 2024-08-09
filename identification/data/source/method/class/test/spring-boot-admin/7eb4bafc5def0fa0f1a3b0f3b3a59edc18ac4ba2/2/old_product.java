public Application getApplication(String id) {
		if (!isRegistered(id)) {
			throw new IllegalArgumentException("Application with ID " + id + " is not registered");
		}
		return registry.get(id);
	}