@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        Set<String> setOfGroup = getStore().readAllGroups();
        StringBuilder sb = new StringBuilder("{ groups:");
        boolean first = true;
        for (String group : setOfGroup) {
            if (!first) {
                sb.append(",");
            }
            first = false;
            sb.append("{\"" + group + "\":\"" + uriInfo.getAbsolutePath() + group + "\"}");
        }
        sb.append("}");
        return Response.ok(sb.toString()).build();
    }