public EntityEdit createEntity(Archetype archetype) {
		Entity e = em.createEntityInstance();
		return e.edit();
	}