public boolean isBefore(@NonNull final DateAndSeqNo other)
	{
		final boolean beforeDate = date.isBefore(other.getDate());

		final boolean sameDateDateAndSmallerSeqNo = date.equals(other.getDate()) && seqNo < other.getSeqNo();

		return beforeDate || sameDateDateAndSmallerSeqNo;
	}