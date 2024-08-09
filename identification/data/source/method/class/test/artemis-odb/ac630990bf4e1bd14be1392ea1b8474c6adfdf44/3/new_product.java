public Entity createEntity() {
		Entity e = em.createEntityInstance();
		e.edit();
		return e;
	}