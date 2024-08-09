@NonNull
	ImmutablePair<TourId, ZonedDateTime> calculateTourAndPreparationDate(
			@NonNull IContextAware context,
			@NonNull SOTrx soTrx,
			@NonNull ZonedDateTime calculationTime,
			@NonNull ZonedDateTime datePromised,
			@NonNull BPartnerLocationId bpartnerLocationId);