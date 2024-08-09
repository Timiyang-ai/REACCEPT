	@Test
	public void getDayOfWeek() {
		// 2017-01-09
		Date date = new Date(117, 0, 9);
		assertThat(DateUtil.getDayOfWeek(date)).isEqualTo(1);

		Date date2 = new Date(117, 0, 15);
		assertThat(DateUtil.getDayOfWeek(date2)).isEqualTo(7);
	}