@Test
	public final void testProcess1() {
		BoundWriter bw = new BoundWriter("bound", 2);
		bw.setWriter(testBufferedWriter);
		bw.process(new Bound(20.123456, -21.987654, 22.555555, -23.234567, "originstring"));
		try {
			testBufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
			fail("IOException");
		}
		// If this test fails, it could be because the regex has broken. There are a number of
		// variations which are valid XML which this regex won't catch. It might need any number of
		// \\s* to account for variable whitespace.
		String regexMatch = "^\\s*<bound\\s*"
		        + "box=['\"]-23.23457,-21.98765,22.55556,20.12346['\"]\\s*"
		        + "origin=['\"]originstring['\"]/>\\s*$";
		assertTrue(testWriter.toString().matches(regexMatch));
	}