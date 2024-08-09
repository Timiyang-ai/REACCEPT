public Entity createEntity(UUID uuid) {
		Entity entity = em.createEntityInstance();
		entity.setUuid(uuid);
		return entity;
	}