@GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlarm(@PathParam("id") final String id) {
        log.info("HTTP GET alarm for id={}", id);

        final AlarmId alarmId = toAlarmId(id);
        final Alarm alarm = get(AlarmService.class).getAlarm(alarmId);

        final ObjectNode result = mapper().createObjectNode();
        result.set("alarm", codec(Alarm.class).encode(alarm, this));
        return ok(result.toString()).build();
    }