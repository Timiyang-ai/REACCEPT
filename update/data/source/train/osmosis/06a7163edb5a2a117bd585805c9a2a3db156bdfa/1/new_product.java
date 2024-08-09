public void process(NodeContainer nodeContainer) {
		nodeBuilder.initialize(nodeContainer.getEntity());
		nodeBuilder.setTimestamp(timestamp);
		
		changeSink.process(
			new ChangeContainer(
				new NodeContainer(
					nodeBuilder.buildEntity()
				),
				action
			)
		);
	}