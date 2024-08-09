public Entity createEntity(Archetype archetype) {
		Entity e = em.createEntityInstance(archetype);
		cm.addComponents(e.getId(), archetype);
		changed.set(e.getId());
		return e;
	}