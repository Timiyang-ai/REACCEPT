	@Test
	public void parseDescriptor() throws Exception {
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("descriptor.xml");
		String descriptor = IOUtils.toString(resourceAsStream, "UTF-8");

		Map<String, String> launchMap = new HashMap<>();
		Map<String, String> postPropMap = new HashMap<>();

		boolean parsed = BasicLTIUtil.parseDescriptor(launchMap, postPropMap, descriptor);
		assertTrue(parsed);
	}