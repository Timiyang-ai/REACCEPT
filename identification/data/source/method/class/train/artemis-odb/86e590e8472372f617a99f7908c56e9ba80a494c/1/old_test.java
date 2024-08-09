	@Test
	public void transmute_twice() {
		Entity e = createEntity(ComponentY.class, ReusedComponent.class);
		world.process();

		assertEquals(2, e.getCompositionId());

		transmuter1.transmute(e);
		assertEquals(0, e.getCompositionId());

		transmuter3.transmute(e);
		assertEquals(3, e.getCompositionId());
	}