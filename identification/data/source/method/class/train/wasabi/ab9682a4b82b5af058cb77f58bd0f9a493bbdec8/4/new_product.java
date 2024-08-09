@GET
    @Path("{experimentID}/assignments")
    @Produces(TEXT_PLAIN)
    @ApiOperation(value = "Download a list of user assignments for a given experiment ID",
            notes = "Shows list of all user assignments for a given experiment ID. Returns both null" +
                    "assignments as well as bucket assignments.",
            response = StreamingOutput.class)
    @Timed
    public Response exportAssignments(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            @QueryParam("context")
            @DefaultValue("PROD")
            @ApiParam(value = "context for the experiment, eg QA, PROD")
            final Context context,

            @QueryParam("ignoreNullBucket")
            @DefaultValue("false")
            @ApiParam(value = "Filtering on the null bucket")
            final String ignoreStringNullBucket,

            @QueryParam("fromDate")
            @ApiParam(value = "from date to download assignments")
            final String fromStringDate,

            @QueryParam("toDate")
            @ApiParam(value = "to date to download assignments")
            final String toStringDate,

            @QueryParam("timeZone")
            @ApiParam(value = "value of the time zone")
            final String timeZoneString,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) throws ParseException {
        try {
            //TODO: Duplicate code: Move to separate method
            if (authorizationHeader != null) {
                Username userName = authorization.getUser(authorizationHeader);
                Experiment experiment = experiments.getExperiment(experimentID);

                if (experiment == null) {
                    throw new ExperimentNotFoundException(experimentID);
                }

                authorization.checkUserPermissions(userName, experiment.getApplicationName(), READ);
            }

            //Initializing the parameters
            Parameters parameters = new Parameters();
            //Setting the filtering behavior on the null buckets --default is set to get all the assignments
            Boolean ignoreNullBucket = FALSE;

            if (ignoreStringNullBucket != null) {
                ignoreNullBucket = Boolean.parseBoolean(ignoreStringNullBucket);
            }

            parameters.setTimeZone(getTimeZone(defaultTimezone));

            //Input format of the dates
            SimpleDateFormat sdf = new SimpleDateFormat(defaultTimeFormat);

            if (timeZoneString != null) {
                TimeZone timeZone = getTimeZone(timeZoneString);

                //Note: TimeZone.getTimeZone doesn't have an inbuilt error catch. Hence, the below check is necessary.
                // Allowed time zones http://tutorials.jenkov.com/java-date-time/java-util-timezone.html
                if (!timeZone.getID().equals(timeZoneString)) {
                    throw new TimeZoneFormatException(timeZoneString);
                }

                sdf.setTimeZone(timeZone);
                // Resetting the default time zone value to user entered time zone.
                parameters.setTimeZone(timeZone);
            }

            if (fromStringDate != null) {
                try {
                    Date fromDate = sdf.parse(fromStringDate);

                    parameters.setFromTime(fromDate);
                } catch (ParseException e) {
                    throw new TimeFormatException(fromStringDate);
                }
            }

            if (toStringDate != null) {
                try {
                    Date toDate = sdf.parse(toStringDate);

                    parameters.setToTime(toDate);
                } catch (ParseException e) {
                    throw new TimeFormatException(toStringDate);
                }
            }

            StreamingOutput streamAssignment = assignments
                    .getAssignmentStream(experimentID, context, parameters, ignoreNullBucket);

            return httpHeader.headers()
                    .header("Content-Disposition", "attachment; filename =\"assignments.csv\"")
                    .entity(streamAssignment).build();
        } catch (Exception exception) {
            LOGGER.error("exportAssignments failed for experimentID={}, context={}, ignoreStringNullBucket={}," +
                            " fromStringDate={}, toStringDate={}, timeZoneString={} with error:",
                    experimentID, context, ignoreStringNullBucket, fromStringDate, toStringDate, timeZoneString,
                    exception);
            throw exception;
        }
    }