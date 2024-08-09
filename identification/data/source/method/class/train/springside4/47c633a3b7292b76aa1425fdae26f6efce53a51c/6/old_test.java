	@Test
	public void isNumber() {
		assertThat(NumberUtil.isNumber("123")).isTrue();
		assertThat(NumberUtil.isNumber("-123.1")).isTrue();
		assertThat(NumberUtil.isNumber("-1a3.1")).isFalse();

		assertThat(NumberUtil.isHexNumber("0x12F")).isTrue();
		assertThat(NumberUtil.isHexNumber("-0x12A3")).isTrue();
		assertThat(NumberUtil.isHexNumber("12A3")).isFalse();
	}