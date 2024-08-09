@Test
	public void stop()
	{
		final StringBuilder path = new StringBuilder();

		TestContainer container = new TestContainer();
		Object result = container.visitChildren(new IVisitor<Component, String>()
		{
			public void component(Component component, IVisit<String> visit)
			{
				path.append(component.getId());
				if ("D".equals(component.getId()))
				{
					visit.stop("RESULT");
				}
			}
		});
		Assert.assertEquals("BCD", path.toString());
		Assert.assertEquals("RESULT", result);
	}