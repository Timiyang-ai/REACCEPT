public void process(EntityContainer entityContainer) {
		EntityContainer writeableContainer;
		Entity entity;
		Collection<Tag> sortedTags;
		
		writeableContainer = entityContainer.getWriteableInstance();
		
		entity = writeableContainer.getEntity();
		sortedTags = sortTags(entity.getTags());
		entity.getTags().clear();
		entity.getTags().addAll(sortedTags);
		
		sink.process(writeableContainer);
	}