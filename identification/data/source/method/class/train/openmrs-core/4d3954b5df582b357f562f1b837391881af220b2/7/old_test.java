	@Test
	public void copy_shouldCopyAllTestOrderFields() throws Exception {
		OrderTest.assertThatAllFieldsAreCopied(new TestOrder(), null);
	}