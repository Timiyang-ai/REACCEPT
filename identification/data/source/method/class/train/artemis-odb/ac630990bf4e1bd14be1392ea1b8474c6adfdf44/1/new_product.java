public Entity createEntity(Archetype archetype) {
		Entity e = em.createEntityInstance(archetype);

		int id = e.getId();
		archetype.transmuter.perform(id);
		cm.setIdentity(e.id, archetype.compositionId);

		batchProcessor.changed.set(id);

		return e;
	}