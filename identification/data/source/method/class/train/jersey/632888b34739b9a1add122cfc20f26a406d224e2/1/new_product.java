@GET
        @Path("responseFilterSync")
        public Response responseFilterSync() {
            return Response.created(URI.create("responseFilterSync")).build();
        }