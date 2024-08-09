public void createAndAddEvents(
			@NonNull final HUTraceEventBuilder builder,
			@NonNull final List<?> models)
	{

		for (final Object model : models)
		{
			final List<I_M_HU_Assignment> huAssignments = huAccessService.retrieveHuAssignments(model);

			for (final I_M_HU_Assignment huAssignment : huAssignments)
			{
				final int topLevelHuId = huAccessService.retrieveTopLevelHuId(huAssignment.getM_HU());
				builder.topLevelHuId(topLevelHuId);
				builder.eventTime(huAssignment.getUpdated().toInstant());

				final List<I_M_HU> vhus;
				if (huAssignment.getVHU_ID() > 0)
				{
					vhus = ImmutableList.of(huAssignment.getVHU());
				}
				else if (huAssignment.getM_TU_HU_ID() > 0)
				{
					vhus = huAccessService.retrieveVhus(huAssignment.getM_TU_HU());
				}
				else if (huAssignment.getM_LU_HU_ID() > 0)
				{
					vhus = huAccessService.retrieveVhus(huAssignment.getM_LU_HU());
				}
				else
				{
					vhus = huAccessService.retrieveVhus(huAssignment.getM_HU());
				}

				for (final I_M_HU vhu : vhus)
				{
					builderSetVhuProductAndQty(builder, vhu)
							.vhuStatus(vhu.getHUStatus());

					huTraceRepository.addEvent(builder.build());
				}
			}
		}
	}