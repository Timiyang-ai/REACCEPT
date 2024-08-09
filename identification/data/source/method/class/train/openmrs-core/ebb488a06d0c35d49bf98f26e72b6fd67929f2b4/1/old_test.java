	@Test
	public void parse_shouldsetPropertiesFromXmlFile() {

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<update configVersion=\"1.0\">"
			+ "<moduleId>formEntry</moduleId>"
			+ "<currentVersion>1.0</currentVersion>" 
			+ "<downloadURL>https://modules.openmrs.org/modules/formentry/formentry-1.0.omod</downloadURL>"
			+ "</update>";
		
		UpdateFileParser parser = new UpdateFileParser(xml);
		parser.parse();

		assertEquals("formEntry", parser.getModuleId());
		assertEquals("1.0", parser.getCurrentVersion());
		assertEquals("https://modules.openmrs.org/modules/formentry/formentry-1.0.omod", parser.getDownloadURL());
	}