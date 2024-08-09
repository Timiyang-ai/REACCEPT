	@Test
	public void urlEncode() {

		String input = "http://locahost/";
		String result = EscapeUtil.urlEncode(input);
		assertThat(result).isEqualTo("http%3A%2F%2Flocahost%2F");
		assertThat(EscapeUtil.urlDecode(result)).isEqualTo(input);

		input = "http://locahost/?query=中文&t=1";
		result = EscapeUtil.urlEncode(input);
		System.out.println(result);

		assertThat(EscapeUtil.urlDecode(result)).isEqualTo(input);
	}