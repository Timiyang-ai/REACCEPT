	@Test
	public void isSameDay() {
		Date date1 = new Date(106, 10, 1);
		Date date2 = new Date(106, 10, 1, 12, 23, 44);
		assertThat(DateUtil.isSameDay(date1, date2)).isTrue();

		Date date3 = new Date(106, 10, 1);
		assertThat(DateUtil.isSameTime(date1, date3)).isTrue();

		Date date5 = new Date(106, 10, 2);
		assertThat(DateUtil.isSameTime(date1, date5)).isFalse();

		Date date4 = new Date(106, 10, 1, 12, 23, 43);
		assertThat(DateUtil.isBetween(date3, date1, date2)).isTrue();
		assertThat(DateUtil.isBetween(date4, date1, date2)).isTrue();

		try {
			DateUtil.isBetween(null, date1, date2);
			fail("should fail before");
		} catch (Exception e) {

		}

		try {
			DateUtil.isBetween(date3, date2, date1);
			fail("should fail before");
		} catch (Exception e) {

		}

		assertThat(DateUtil.isBetween(date5, date1, date2)).isFalse();
	}