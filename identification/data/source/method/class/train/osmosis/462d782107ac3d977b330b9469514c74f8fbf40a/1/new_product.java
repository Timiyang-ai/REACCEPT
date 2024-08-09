@Override
	public void process(Dataset dataset) {
		DatasetContext dsCtx = dataset.createReader();
		
		try {
			EntityManager<Node> nodeManager = dsCtx.getNodeManager();
			OsmUser user;
			Node node;
			
			// Create the user for edits to be performed under. This is an existing user with an
			// updated name.
			user = new OsmUser(10, "user10b");
			
			// Modify node 1 to add a new tag.
			node = nodeManager.getEntity(1).getWriteableInstance();
			node.setUser(user);
			node.getTags().add(new Tag("change", "new tag"));
			nodeManager.modifyEntity(node);
			
			// Delete node 6.
			nodeManager.removeEntity(6);
			
			// Add node 7 using the NONE user.
			node = new Node(7, 16, buildDate("2008-01-02 18:19:20"), OsmUser.NONE, 0, -11, -12);
			node.getTags().addAll(
					Arrays.asList(new Tag[]{new Tag("created_by", "Me7"), new Tag("change", "new node")}));
			nodeManager.addEntity(node);
			
			dsCtx.complete();
			
		} finally {
			dsCtx.release();
		}
	}