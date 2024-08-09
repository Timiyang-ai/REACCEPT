public void transmute(int entityId) {
		if (world.editPool.isPendingDeletion(entityId))
			return;

		if (!world.getEntityManager().isActive(entityId))
			throw new RuntimeException("Issued transmute on deleted " + entityId);

		TransmuteOperation operation = getOperation(entityId);
		operation.perform(entityId, world.getComponentManager());
		world.getEntityManager().setIdentity(entityId, operation.compositionId);

		world.changed.set(entityId);
	}