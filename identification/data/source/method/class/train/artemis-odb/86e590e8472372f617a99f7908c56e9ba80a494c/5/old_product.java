public void transmute(int entityId) {
		TransmuteOperation operation = getOperation(entityId);

		operation.perform(entityId, world.getComponentManager());
		world.getEntityManager().setIdentity(entityId, operation.compositionId);

		world.changed.set(entityId);
	}