public RoutingRequestBuilder addTripParameters(TripParameters tripParams) {
        if (tripParams.isSetAllowed_modes()) {
            Set<TravelMode> allowedModes = tripParams.getAllowed_modes();
            setTravelModes(new TravelModeSet(allowedModes));
        }

        // Set trip timing information
        if (tripParams.isSetStart_time()) {
            setStartTime(tripParams.getStart_time());
        } else if (tripParams.isSetArrive_by()) {
            setArriveBy(tripParams.getArrive_by());
        }

        setOrigin(tripParams.getOrigin().getLat_lng());
        setDestination(tripParams.getDestination().getLat_lng());

        return this;
    }