public Entity createEntity() {
		Entity e = em.createEntityInstance();
		batchProcessor.changed.unsafeSet(e.getId());
		return e;
	}