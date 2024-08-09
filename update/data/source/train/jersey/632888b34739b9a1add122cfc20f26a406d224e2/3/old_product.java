@GET
        @Path("responseFilterAsync")
        public void responseFilterAsync(@Suspended final AsyncResponse asyncResponse) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Response result = Response.created(getUriBuilder().segment("responseFilterAsync").build()).build();
                    asyncResponse.resume(result);
                }
            }).start();
        }