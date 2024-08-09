public void transmute(int entityId) {
		if (batchProcessor.isDeleted(entityId))
			return;

		if (!em.isActive(entityId))
			throw new RuntimeException("Issued transmute on deleted " + entityId);

		TransmuteOperation operation = getOperation(entityId);
		operation.perform(entityId);
		cm.setIdentity(entityId, operation.compositionId);

		batchProcessor.changed.set(entityId);
	}