@Test
	public void testDoGet() throws ServletException, IOException {
		doGet(Collections.<HttpParameter, String> emptyMap(), true);

		setProperty(Parameter.ALLOWED_ADDR_PATTERN, "256.*");
		try {
			doGet(Collections.<HttpParameter, String> emptyMap(), false);
			setProperty(Parameter.ALLOWED_ADDR_PATTERN, ".*");
			doGet(Collections.<HttpParameter, String> emptyMap(), false);
		} finally {
			setProperty(Parameter.ALLOWED_ADDR_PATTERN, null);
		}
	}