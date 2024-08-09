	@Test
	public void utf8EncodedLength() {
		assertThat(MoreStringUtil.utf8EncodedLength("ab12")).isEqualTo(4);
		assertThat(MoreStringUtil.utf8EncodedLength("中文")).isEqualTo(6);
	}