	@SuppressWarnings("static-method")
	@Test
	public void test_process_one_active() {
		World w = new World(new WorldConfiguration()
			.setSystem(new IteratorTestSystem(1)));

		Entity e = w.createEntity();
		e.edit().add(new C());

		w.process();
	}