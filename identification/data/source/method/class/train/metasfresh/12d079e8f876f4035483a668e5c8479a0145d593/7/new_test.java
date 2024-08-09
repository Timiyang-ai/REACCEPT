	@Test
	public void updateSchedules_emptyList()
	{
		final List<OlAndSched> olAndScheds = new ArrayList<>();

		shipmentScheduleUpdater.updateSchedules(Env.getCtx(), olAndScheds);
	}