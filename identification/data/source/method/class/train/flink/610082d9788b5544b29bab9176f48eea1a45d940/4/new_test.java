@Test
	public void testConfigure() {
		when(this.config.getString(Matchers.matches(TextInputFormat.FORMAT_PAIR_DELIMITER), Matchers.anyString()))
			.thenReturn("\n");
		format.configure(this.config);
		verify(this.config, times(1)).getString(Matchers.any(String.class), Matchers.any(String.class));
		assertEquals("\n", new String(format.getDelimiter()));

		when(this.config.getString(Matchers.matches(TextInputFormat.FORMAT_PAIR_DELIMITER), Matchers.anyString()))
			.thenReturn("&-&");
		format.configure(this.config);
		verify(this.config, times(2)).getString(Matchers.any(String.class), Matchers.any(String.class));
		assertEquals("&-&", new String(format.getDelimiter()));
	}