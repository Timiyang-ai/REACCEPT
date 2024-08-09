@Test
	public void testDoGet() throws ServletException, IOException {
		doGet(Collections.<String, String> emptyMap(), true);

		setProperty(Parameter.ALLOWED_ADDR_PATTERN, "256.*");
		try {
			doGet(Collections.<String, String> emptyMap(), false);
			setProperty(Parameter.ALLOWED_ADDR_PATTERN, ".*");
			doGet(Collections.<String, String> emptyMap(), false);
		} finally {
			setProperty(Parameter.ALLOWED_ADDR_PATTERN, null);
		}
	}