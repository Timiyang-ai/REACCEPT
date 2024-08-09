static public Timestamp getDay(@Nullable final java.util.Date dayTime)
	{
		if (dayTime == null)
		{
			return getDay(System.currentTimeMillis());
		}
		return getDay(dayTime.getTime());
	}