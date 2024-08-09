public static Interval delta(long earlierTime_millis, long laterTime_millis)
	{
		return Interval.millis(laterTime_millis - earlierTime_millis);
	}