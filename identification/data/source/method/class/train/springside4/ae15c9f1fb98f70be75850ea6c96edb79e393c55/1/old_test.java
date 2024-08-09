	@Test
	public void formatFriendlyTimeSpanByNow() throws ParseException {
		try {
			Date now = DateFormatUtil.DEFAULT_ON_SECOND_FORMAT.parse("2016-12-11 23:30:00");

			ClockUtil.useDummyClock(now);

			Date lessOneSecond = DateFormatUtil.DEFAULT_FORMAT.parse("2016-12-11 23:29:59.500");
			assertThat(DateFormatUtil.formatFriendlyTimeSpanByNow(lessOneSecond)).isEqualTo("刚刚");

			Date lessOneMinute = DateFormatUtil.DEFAULT_FORMAT.parse("2016-12-11 23:29:55.000");
			assertThat(DateFormatUtil.formatFriendlyTimeSpanByNow(lessOneMinute)).isEqualTo("5秒前");

			Date lessOneHour = DateFormatUtil.DEFAULT_ON_SECOND_FORMAT.parse("2016-12-11 23:00:00");
			assertThat(DateFormatUtil.formatFriendlyTimeSpanByNow(lessOneHour)).isEqualTo("30分钟前");

			Date today = DateFormatUtil.DEFAULT_ON_SECOND_FORMAT.parse("2016-12-11 1:00:00");
			assertThat(DateFormatUtil.formatFriendlyTimeSpanByNow(today)).isEqualTo("今天01:00");

			Date yesterday = DateFormatUtil.DEFAULT_ON_SECOND_FORMAT.parse("2016-12-10 1:00:00");
			assertThat(DateFormatUtil.formatFriendlyTimeSpanByNow(yesterday)).isEqualTo("昨天01:00");

			Date threeDayBefore = DateFormatUtil.DEFAULT_ON_SECOND_FORMAT.parse("2016-12-09 1:00:00");
			assertThat(DateFormatUtil.formatFriendlyTimeSpanByNow(threeDayBefore)).isEqualTo("2016-12-09");

		} finally {

			ClockUtil.useDefaultClock();
		}
	}