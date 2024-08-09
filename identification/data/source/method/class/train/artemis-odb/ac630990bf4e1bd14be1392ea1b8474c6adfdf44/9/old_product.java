public Entity createEntity(Archetype archetype) {
		Entity e = em.createEntityInstance(archetype);
		added.add(e);
		return e;
	}