@Test
	public void testAnalyzeAll_BrokenZip() {
		final byte[] buffer = new byte[30];
		buffer[0] = 0x50;
		buffer[1] = 0x4b;
		buffer[2] = 0x03;
		buffer[3] = 0x04;
		Arrays.fill(buffer, 4, buffer.length, (byte) 0x42);
		try {
			analyzer.analyzeAll(new ByteArrayInputStream(buffer), "Test.zip");
			fail("expected exception");
		} catch (IOException e) {
			assertEquals("Error while analyzing Test.zip.", e.getMessage());
		}
	}