	@Test
	public void formatXPath() {
		assertThat(XPathHelper.formatXPath("//ul/a"), is("//UL/A"));
		assertThat(XPathHelper.formatXPath("/div//span"), is("/DIV//SPAN"));
		assertThat(XPathHelper.formatXPath("//ul[@CLASS=\"Test\"]"), is("//UL[@class=\"Test\"]"));
		assertThat(XPathHelper.formatXPath("//ul[@CLASS=\"Test\"]/a"),
				is("//UL[@class=\"Test\"]/A"));

	}