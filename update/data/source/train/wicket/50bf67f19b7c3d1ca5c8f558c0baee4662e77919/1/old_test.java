@Test
	public void testSetBodyModel()
	{
		final String linkBody = "Link body";

		MockPageWithLink mockPageWithLink = new MockPageWithLink();
		AbstractLink link = new AbstractLink("link")
		{
			private static final long serialVersionUID = 1L;
		};
		link.setMarkupId("link");
		link.setBodyModel(Model.of(linkBody));
		mockPageWithLink.add(link);

		wicketTester.startPage(mockPageWithLink);
		TagTester tagTester = wicketTester.getTagById("link");
		Assert.assertEquals(linkBody, tagTester.getValue());
	}