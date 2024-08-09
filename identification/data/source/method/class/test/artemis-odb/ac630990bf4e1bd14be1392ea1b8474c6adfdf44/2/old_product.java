public Entity createEntity() {
		Entity e = em.createEntityInstance();
		changed.set(e.getId());
		return e;
	}