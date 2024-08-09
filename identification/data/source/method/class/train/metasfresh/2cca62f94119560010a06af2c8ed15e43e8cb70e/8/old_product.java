static public Timestamp getDay(int year, int month, int day)
	{
		int hour = 0;
		int minute = 0;
		int second = 0;
		return getDay(year, month, day, hour, minute, second);
	}