public static Itinerary generateItinerary(GraphPath path, boolean showIntermediateStops, Locale requestedLocale) {
        Itinerary itinerary = new Itinerary();

        State[] states = new State[path.states.size()];
        State lastState = path.states.getLast();
        states = path.states.toArray(states);

        Edge[] edges = new Edge[path.edges.size()];
        edges = path.edges.toArray(edges);

        Graph graph = path.getRoutingContext().graph;

        FareService fareService = graph.getService(FareService.class);

        State[][] legsStates = sliceStates(states);

        if (fareService != null) {
            itinerary.fare = fareService.getCost(path);
        }

        for (State[] legStates : legsStates) {
            itinerary.addLeg(generateLeg(graph, legStates, showIntermediateStops, requestedLocale));
        }

        addWalkSteps(graph, itinerary.legs, legsStates, requestedLocale);

        fixupLegs(itinerary.legs, legsStates);

        itinerary.duration = lastState.getElapsedTimeSeconds();
        itinerary.startTime = makeCalendar(states[0]);
        itinerary.endTime = makeCalendar(lastState);

        calculateTimes(itinerary, states);

        calculateElevations(itinerary, edges);

        itinerary.walkDistance = lastState.getWalkDistance();

        itinerary.transfers = lastState.getNumBoardings();
        if (itinerary.transfers > 0 && !(states[0].getVertex() instanceof OnboardDepartVertex)) {
            itinerary.transfers--;
        }

        return itinerary;
    }