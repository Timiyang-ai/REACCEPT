@GET
    @Produces("text/plain")
    @Suspend
    public void get(@Context final ExecutionContext context) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    context.resume(MESSAGE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }