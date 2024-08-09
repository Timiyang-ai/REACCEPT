public boolean isBefore(@NonNull final DateAndSeqNo other)
	{
		// note that we avoid using equals here, a timestamp and a date that are both "Date" might not be equal even if they have the same time.
		if (date.isBefore(other.getDate()))
		{
			return true;
		}
		if (date.isAfter(other.getDate()))
		{
			return false;
		}
		return seqNo < other.getSeqNo();
	}