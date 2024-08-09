@Test
	public void testDepth_Component()
	{
		page.send(c1, Broadcast.DEPTH, new Payload());
		assertPath(c12, c134, c135, c13, c1);
	}