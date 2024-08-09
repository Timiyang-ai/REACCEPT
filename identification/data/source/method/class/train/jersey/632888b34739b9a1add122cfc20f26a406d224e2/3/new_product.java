@GET
        @Path("responseFilterAsync")
        public void responseFilterAsync(@Suspended final AsyncResponse asyncResponse) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Response result = Response.created(URI.create("responseFilterAsync")).build();
                    asyncResponse.resume(result);
                }
            }).start();
        }