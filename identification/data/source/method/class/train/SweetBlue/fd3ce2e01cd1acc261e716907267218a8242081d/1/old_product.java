public static Interval delta(long earlierTime_millis, long laterTime_millis)
	{
		return Interval.milliseconds(laterTime_millis - earlierTime_millis);
	}