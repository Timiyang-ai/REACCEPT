@GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlarm(@PathParam("id") String id) {
        log.debug("HTTP GET alarm for id={}", id);

        AlarmId alarmId = AlarmId.alarmId(id);
        Alarm alarm = get(AlarmService.class).getAlarm(alarmId);

        ObjectNode result = new ObjectMapper().createObjectNode();
        result.set("alarm", new AlarmCodec().encode(alarm, this));
        return ok(result.toString()).build();
    }