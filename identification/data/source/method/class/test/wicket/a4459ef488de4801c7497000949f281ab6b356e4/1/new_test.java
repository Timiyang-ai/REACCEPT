@Test
	public void testDepth_Component()
	{
		page.send(c6, Broadcast.DEPTH, new Payload());
		assertPath(c6);
	}