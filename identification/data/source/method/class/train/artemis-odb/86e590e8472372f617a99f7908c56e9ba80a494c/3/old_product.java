public void transmute(int entityId) {
		if (world.deleted.get(entityId))
			return;

		EntityManager em = world.getEntityManager();
		if (!em.isActive(entityId))
			throw new RuntimeException("Issued transmute on deleted " + entityId);

		TransmuteOperation operation = getOperation(entityId);
		operation.perform(entityId, world.getComponentManager());
		em.setIdentity(entityId, operation.compositionId);

		world.changed.set(entityId);
	}