	@Test
	public void copyTest() {
		byte[] bytes = "AAABBB".getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		ByteBuffer buffer2 = BufferUtil.copy(buffer, ByteBuffer.allocate(5));
		Assert.assertEquals("AAABB", StrUtil.utf8Str(buffer2));
	}