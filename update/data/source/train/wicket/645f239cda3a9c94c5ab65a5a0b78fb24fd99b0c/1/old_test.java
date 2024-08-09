@Test
	public void hasChildTag()
	{
		TagTester tester = TagTester.createTagByAttribute(MARKUP_1, "id", "test");

		assertTrue(tester.hasChildTag("span"));
		assertTrue(tester.hasChildTag("SPAN"));

		assertFalse(tester.hasChildTag("span "));
		assertFalse(tester.hasChildTag("p"));

		try
		{
			tester.hasChildTag("");
			fail();
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}

		try
		{
			tester.hasChildTag(null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}


		tester = TagTester.createTagByAttribute("<p id=\"test\">mock</p>", "id", "test");

		assertFalse(tester.hasChildTag("span"));
		assertFalse(tester.hasChildTag("p"));
	}