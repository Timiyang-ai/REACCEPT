@GET
        @Path("responseFilterSync")
        public Response responseFilterSync() {
            return Response.created(getUriBuilder().segment("responseFilterSync").build()).build();
        }