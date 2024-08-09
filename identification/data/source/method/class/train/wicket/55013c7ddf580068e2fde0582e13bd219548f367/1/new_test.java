@Test
	public void getLastModified() throws Exception
	{
		URL url = new URL("http://wicket.apache.org/learn/books/wia.png");
		Instant lastModified = Connections.getLastModified(url);
		assertNotNull(lastModified);
		assertNotEquals(lastModified.toEpochMilli(), 0L);
	}