public Entity createEntity(Archetype archetype) {
		Entity e = em.createEntityInstance(archetype);

		int id = e.getId();
		archetype.transmuter.perform(id);
		batchProcessor.changed.set(id);

		return e;
	}