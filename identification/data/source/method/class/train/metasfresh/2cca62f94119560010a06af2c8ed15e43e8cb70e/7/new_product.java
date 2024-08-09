@Deprecated
	static public Timestamp getDay(@Nullable final Date dayTime)
	{
		if (dayTime == null)
		{
			return getDay(System.currentTimeMillis());
		}
		return getDay(dayTime.getTime());
	}