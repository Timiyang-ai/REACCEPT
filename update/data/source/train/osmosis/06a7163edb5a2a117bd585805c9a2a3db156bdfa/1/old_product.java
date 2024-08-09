public void process(NodeContainer nodeContainer) {
		Node inputEntity = nodeContainer.getEntity();
		
		changeSink.process(
			new ChangeContainer(
				new NodeContainer(
					new Node(
						inputEntity.getId(),
						inputEntity.getVersion(),
						timestamp,
						inputEntity.getUser(),
						inputEntity.getLatitude(),
						inputEntity.getLongitude()
					)
				),
				action
			)
		);
	}