public void process(NodeContainer nodeContainer) {
		Node inputEntity = nodeContainer.getEntity();
		
		changeSink.process(
			new ChangeContainer(
				new NodeContainer(
					new Node(
						inputEntity.getId(),
						timestamp,
						inputEntity.getUser(),
						inputEntity.getVersion(),
						inputEntity.getLatitude(),
						inputEntity.getLongitude()
					)
				),
				action
			)
		);
	}