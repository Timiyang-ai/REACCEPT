static public Timestamp getDay(
			final int year,
			final int month,
			final int day,
			final int hour,
			final int minute,
			final int second)
	{
		final int yearToUse;
		if (year < 50)
		{
			yearToUse = year + 2000;
		}
		else if (year < 100)
		{
			yearToUse = year + 1900;
		}
		else
		{
			yearToUse = year;
		}

		Check.errorIf(month < 1 || month > 12, "Invalid Month: {}", month);
		Check.errorIf(day < 1 || day > 31, "Invalid Day: {}", day);

		final GregorianCalendar cal = new GregorianCalendar(yearToUse, month - 1, day, hour, minute, second);
		return new Timestamp(cal.getTimeInMillis());
	}