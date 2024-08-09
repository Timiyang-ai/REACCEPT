@GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Pings the server",
            notes = "Also returns the status of other components. Uses metrics-healthchecks and pings/check connections to MySql and Cassandra.",
            response = ComponentHealthList.class)
    @Timed
    public Response ping() {
        try {
            boolean status = true;
            Map<String, HealthCheck.Result> results = healthChecks.runHealthChecks();
            List<ComponentHealth> chs = new ArrayList<>();

            for (Entry<String, HealthCheck.Result> entry : results.entrySet()) {
                String serverId = entry.getKey();
                ComponentHealth h = new ComponentHealth(serverId);

                chs.add(h);
                h.setHealthy(entry.getValue().isHealthy());

                if (!entry.getValue().isHealthy()) {
                    status = false;

                    h.setDetailedMessage(entry.getValue().getMessage());
                }
            }

            ComponentHealthList componentHealthList = new ComponentHealthList(chs);

            componentHealthList.setVersion(httpHeader.getApplicationName());

            return httpHeader.headers(
                    status ? OK : SERVICE_UNAVAILABLE).type(APPLICATION_JSON_TYPE).entity(componentHealthList).build();
        } catch (Exception exception) {
            LOGGER.error("ping failed with error:", exception);
            throw exception;
        }
    }