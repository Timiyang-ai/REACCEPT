public static Timestamp getDay(final long time)
	{
		final long timeToUse = time > 0 ? time : SystemTime.millis();

		// note-ts: not using a locale because this method may be used during early startup
		// (and I don't see what for we need a locale)
		// GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
		final GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(timeToUse);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTimeInMillis());
	}