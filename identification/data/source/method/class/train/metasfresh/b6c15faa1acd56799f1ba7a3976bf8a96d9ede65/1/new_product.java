public boolean addEvent(@NonNull final HUTraceEvent huTraceEvent)
	{
		final HUTraceEventQuery query = huTraceEvent.asQuery();

		final List<HUTraceEvent> existingDbRecords = RetrieveDbRecordsUtil.query(huTraceEvent.asQuery());
		final boolean inserted = existingDbRecords.isEmpty();

		if (inserted)
		{
			final I_M_HU_Trace dbRecord = newInstance(I_M_HU_Trace.class);
			logger.info("Found no existing M_HU_Trace record; creating new one; query={}", query);

			HuTraceEventToDbRecordUtil.copyToDbRecord(huTraceEvent, dbRecord);
			save(dbRecord);
		}
		else
		{
			Check.errorIf(existingDbRecords.size() > 1,
					"Expected only one M_HU_Trace record for the given query, but found {}; query={}, M_HU_Trace records={}",
					existingDbRecords.size(), query, existingDbRecords);

			HUTraceEvent existingHuTraceEvent = existingDbRecords.get(0);
			logger.info("Found exiting HUTraceEvent record with ID={}; nothing to do; query={}", existingHuTraceEvent.getHuTraceEventId(), query);
		}

		return inserted;
	}