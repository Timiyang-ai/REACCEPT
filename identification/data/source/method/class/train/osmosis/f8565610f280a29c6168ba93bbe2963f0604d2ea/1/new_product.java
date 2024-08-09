public void process(RelationContainer relationContainer) {
		Relation inputEntity = relationContainer.getEntity();
		Relation outputEntity;
		
		outputEntity = new Relation(inputEntity.getId(),
							timestamp,
							inputEntity.getUserName(),
							inputEntity.getUserId(),
							inputEntity.getVersion());
		
		outputEntity.addTags(inputEntity.getTagList());
		
		changeSink.process(
			new ChangeContainer(
				new RelationContainer(outputEntity),
				action
			)
		);
	}