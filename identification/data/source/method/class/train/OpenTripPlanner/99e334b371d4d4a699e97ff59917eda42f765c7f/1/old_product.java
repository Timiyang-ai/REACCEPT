public RoutingRequestBuilder addTripParameters(TripParameters tripParams) {
		if (tripParams.isSetAllowed_modes()) {
			Set<TravelMode> allowedModes = tripParams.getAllowed_modes();
			setTravelModes(new TravelModeSet(allowedModes));
		}

		setOrigin(tripParams.getOrigin().getLat_lng());
		setDestination(tripParams.getDestination().getLat_lng());

		return this;
	}