public Entity createEntity(UUID uuid) {
		return em.createEntityInstance(uuid);
	}