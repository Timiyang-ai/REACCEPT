	@Test
	public void split() {

		List<String> result = MoreStringUtil.split("192.168.0.1", '.', 4);
		assertThat(result).hasSize(4).containsSequence("192", "168", "0", "1");

		result = MoreStringUtil.split("192.168..1", '.', 4);
		assertThat(result).hasSize(3).containsSequence("192", "168", "1");

		result = MoreStringUtil.split("192.168.0.", '.', 4);
		assertThat(result).hasSize(3).containsSequence("192", "168", "0");

		assertThat(MoreStringUtil.split(null, '.', 4)).isNull();

		assertThat(MoreStringUtil.split("", '.', 4)).hasSize(0);

		Splitter splitter =MoreStringUtil.charsSplitter("/\\").omitEmptyStrings();
		result = splitter.splitToList("/a/b/c");
		assertThat(result).hasSize(3).containsSequence("a", "b", "c");

		result =  splitter.splitToList("\\a\\b\\c");
		assertThat(result).hasSize(3).containsSequence( "a", "b", "c");

	}