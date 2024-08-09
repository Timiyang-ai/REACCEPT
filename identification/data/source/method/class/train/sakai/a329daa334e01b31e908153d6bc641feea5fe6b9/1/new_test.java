	@Test
	public void prepareForExport() throws Exception {
		String forExport = BasicLTIUtil.prepareForExport(null);
		assertNull(forExport);

		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("descriptor_secure.xml");
		String descriptor = IOUtils.toString(resourceAsStream, "UTF-8");

		assertTrue(descriptor.contains("x-secure"));

		forExport = BasicLTIUtil.prepareForExport(descriptor);
		assertFalse(forExport.contains("x-secure"));

		resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("descriptor_empty.xml");
		descriptor = IOUtils.toString(resourceAsStream, "UTF-8");

		forExport = BasicLTIUtil.prepareForExport(descriptor);
		assertNull(forExport);
	}