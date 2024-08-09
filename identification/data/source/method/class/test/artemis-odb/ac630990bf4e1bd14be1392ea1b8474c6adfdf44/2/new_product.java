public Entity createEntity() {
		Entity e = em.createEntityInstance();
		batchProcessor.changed.set(e.getId());
		return e;
	}