	@Test
	public void getVarFromQueryString() {

		assertThat(UrlUtils.getVarFromQueryString("page", "?sub=1&userid=123&page=home&goto=0"),
				is("home"));
		assertThat(UrlUtils.getVarFromQueryString(null, "?sub=1&userid=123&page=home&goto=0"),
				is(nullValue()));
		assertThat(UrlUtils.getVarFromQueryString("page", ""), is(nullValue()));
		assertThat(
				UrlUtils.getVarFromQueryString("page", "?sub=1&userid=123&NotPage=home&goto=0"),
				is(nullValue()));
		assertThat(UrlUtils.getVarFromQueryString("page",
				"?sub=1&userid=123&page=home=moreStringInfo&goto=0"), is(nullValue()));
	}