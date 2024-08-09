@Test
	public void stripJSessionId()
	{
		String url = "http://localhost/abc";
		assertEquals(url, Strings.stripJSessionId(url));
		assertEquals(url + "/", Strings.stripJSessionId(url + "/"));
		assertEquals(url + "?param", Strings.stripJSessionId(url + "?param"));
		assertEquals(url + "?param=a;b", Strings.stripJSessionId(url + "?param=a;b"));
		assertEquals(url + "/?param", Strings.stripJSessionId(url + "/?param"));
		assertEquals(url, Strings.stripJSessionId(url + ";jsessionid=12345"));
		assertEquals(url + "?param", Strings.stripJSessionId(url + ";jsessionid=12345?param"));
		assertEquals(url + "?param=a;b",
			Strings.stripJSessionId(url + ";jsessionid=12345?param=a;b"));

		// WICKET-4816
		assertEquals(url + ";a=b;c=d",
				Strings.stripJSessionId(url + ";a=b;c=d;jsessionid=12345"));
		assertEquals(url + ";a=b;c=d?param=a;b",
				Strings.stripJSessionId(url + ";a=b;c=d;jsessionid=12345?param=a;b"));
	}