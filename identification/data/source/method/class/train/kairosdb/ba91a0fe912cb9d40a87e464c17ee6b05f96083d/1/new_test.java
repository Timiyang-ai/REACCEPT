	private static void putDataPoints(DataPointSet dps) throws DatastoreException
	{
		for (DataPoint dataPoint : dps.getDataPoints())
		{
			s_eventBus.createPublisher(DataPointEvent.class).post(new DataPointEvent(dps.getName(), dps.getTags(), dataPoint, 0));
		}
	}