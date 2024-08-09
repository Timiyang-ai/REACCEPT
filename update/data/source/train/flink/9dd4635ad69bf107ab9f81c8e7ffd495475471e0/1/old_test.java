@Test
	public void testConfigure() {
		when(this.config.getString(Matchers.matches(DelimitedInputFormat.RECORD_DELIMITER), Matchers.anyString()))
			.thenReturn("\n");
		when(this.config.getString(Matchers.matches(FileInputFormat.FILE_PARAMETER_KEY), Matchers.anyString()))
		.thenReturn("file:///some/file/that/will/not/be/read");
		
		format.configure(this.config);
		verify(this.config, times(4)).getString(Matchers.any(String.class), Matchers.any(String.class));
		assertEquals("\n", new String(format.getDelimiter()));

		when(this.config.getString(Matchers.matches(DelimitedInputFormat.RECORD_DELIMITER), Matchers.anyString()))
			.thenReturn("&-&");
		format.configure(this.config);
		verify(this.config, times(8)).getString(Matchers.any(String.class), Matchers.any(String.class));
		assertEquals("&-&", new String(format.getDelimiter()));
	}