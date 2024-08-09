	private Entity createEntity(World world)
	{
		Entity e = world.createEntity();
		e.edit().create(ComponentY.class);
		return e;
	}