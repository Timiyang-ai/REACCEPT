@Test
	public void testConfigure() {
		this.config.setString("delimited-format.delimiter", "\n");
		
		format.configure(this.config);
		assertEquals("\n", new String(format.getDelimiter()));

		this.config.setString("delimited-format.delimiter", "&-&");
		format.configure(this.config);
		assertEquals("&-&", new String(format.getDelimiter()));
	}