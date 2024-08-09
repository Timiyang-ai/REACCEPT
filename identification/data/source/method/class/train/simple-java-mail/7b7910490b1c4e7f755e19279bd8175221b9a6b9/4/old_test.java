	@Test
	public void parsePropertyValue() {
		assertThat(ConfigLoader.parsePropertyValue("simple string value")).isEqualTo("simple string value");
		assertThat(ConfigLoader.parsePropertyValue("12345")).isEqualTo(12345);
		assertThat(ConfigLoader.parsePropertyValue("123d45")).isEqualTo("123d45");
		assertThat(ConfigLoader.parsePropertyValue("0")).isEqualTo(false);
		assertThat(ConfigLoader.parsePropertyValue("false")).isEqualTo(false);
		assertThat(ConfigLoader.parsePropertyValue("no")).isEqualTo(false);
		assertThat(ConfigLoader.parsePropertyValue("1")).isEqualTo(true);
		assertThat(ConfigLoader.parsePropertyValue("true")).isEqualTo(true);
		assertThat(ConfigLoader.parsePropertyValue("yes")).isEqualTo(true);
		assertThat(ConfigLoader.parsePropertyValue("yesno")).isEqualTo("yesno");
		assertThat(ConfigLoader.parsePropertyValue("SMTP")).isEqualTo(TransportStrategy.SMTP);
		assertThat(ConfigLoader.parsePropertyValue("SMTP_TLS")).isEqualTo(TransportStrategy.SMTP_TLS);
	}