@Test
	public void getLastModified() throws Exception
	{
		URL url = new URL("http://wicket.apache.org/learn/books/wia.png");
		Time lastModified = Connections.getLastModified(url);
		assertNotNull(lastModified);
		assertNotEquals(lastModified.getMilliseconds(), 0L);
	}