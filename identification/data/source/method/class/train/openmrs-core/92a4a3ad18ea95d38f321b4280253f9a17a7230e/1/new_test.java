	@Test
	public void storeProperties_shouldEscapeSlashes() throws IOException {
		Charset utf8 = StandardCharsets.UTF_8;
		String expectedProperty = "blacklistRegex";
		String expectedValue = "[^\\p{InBasicLatin}\\p{InLatin1Supplement}]";
		Properties properties = new Properties();
		properties.setProperty(expectedProperty, expectedValue);

		ByteArrayOutputStream actual = new ByteArrayOutputStream();
		ByteArrayOutputStream expected = new ByteArrayOutputStream();
		
		OpenmrsUtil.storeProperties(properties, actual, null);
		
		// Java's underlying implementation correctly writes:
		// blacklistRegex=[^\\p{InBasicLatin}\\p{InLatin1Supplement}]
		// This method didn't exist in Java 5, which is why we wrote a utility method in the first place, so we should
		// just get rid of our own implementation, and use the underlying java one.
		properties.store(new OutputStreamWriter(expected, utf8), null);
		
		assertThat(actual.toByteArray(), is(expected.toByteArray()));
	}