public Collection<Object> findPatients(String searchValue, boolean includeVoided) {
		return findBatchOfPatients(searchValue, includeVoided, null, null);
	}