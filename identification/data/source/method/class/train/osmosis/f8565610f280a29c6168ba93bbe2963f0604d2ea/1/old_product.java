public void process(RelationContainer relationContainer) {
		Relation inputEntity = relationContainer.getEntity();
		Relation outputEntity;
		
		outputEntity = new Relation(inputEntity.getId(), timestamp, inputEntity.getUser());
		
		outputEntity.addTags(inputEntity.getTagList());
		
		changeSink.process(
			new ChangeContainer(
				new RelationContainer(outputEntity),
				action
			)
		);
	}