public Entity createEntity(Archetype archetype) {
		Entity e = em.createEntityInstance(archetype);
		cm.addComponents(e, archetype);
		added.set(e.id);
		return e;
	}