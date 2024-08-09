@Override
	public void process(RelationContainer relation) {
		Relation oldEntity;
		com.bretth.osmosis.core.domain.v0_6.Relation newEntity;
		
		oldEntity = relation.getEntity();
		newEntity = new com.bretth.osmosis.core.domain.v0_6.Relation(
			oldEntity.getId(),
			1,
			oldEntity.getTimestamp(),
			migrateUser(oldEntity.getUser())
		);
		newEntity.addTags(migrateTags(oldEntity));
		newEntity.addMembers(migrateRelationMembers(oldEntity));
		
		sink.process(new com.bretth.osmosis.core.container.v0_6.RelationContainer(newEntity));
	}