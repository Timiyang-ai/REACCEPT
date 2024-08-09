@POST
    @Path("applications/{applicationName}/experiments/{experimentLabel}/users/{userID}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Submit a single event or a batch of events for the specified assigned user(customer)",
            notes = "*NOTE*: For a given user, please make sure that you have the user assignment done using the " +
                    "assignments API before using this API. An event is either an impression, indicating the user " +
                    "has been exposed to the treatment, or an action, indicating that the user has done something " +
                    "that you want to track. Please record impressions first and then action - use event " +
                    "name = \"IMPRESSION\" for impressions.")
    @Timed
    public Response recordEvents(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @PathParam("experimentLabel")
            @ApiParam(value = "Experiment Label")
            final Experiment.Label experimentLabel,

            @PathParam("userID")
            @ApiParam(value = "Customer User ID that is already assigned using assignments API")
            final User.ID userID,

            @ApiParam(name = "eventList", required = true, value = "For impression", defaultValue = DEFAULT_EVENT)
            final EventList eventList)
            throws Exception {
        Set<Context> contextSet = new HashSet<>();

        eventList.getEvents().forEach(event -> contextSet.add(event.getContext()));

        events.recordEvents(applicationName, experimentLabel, userID, eventList, contextSet);

        return httpHeader.headers(CREATED).build();
    }