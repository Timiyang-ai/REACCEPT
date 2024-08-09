public void transmute(int entityId) {
		if (!isValid(entityId)) return;

		TransmuteOperation operation = getOperation(entityId);
		operation.perform(entityId);
		entityToIdentity.unsafeSet(entityId, operation.compositionId);
	}