public Entity createEntity(Archetype archetype) {
		Entity e = em.createEntityInstance();

		int id = e.getId();
		archetype.transmuter.perform(id);
		cm.setIdentity(e.id, archetype.compositionId);

		batchProcessor.changed.unsafeSet(id);

		return e;
	}