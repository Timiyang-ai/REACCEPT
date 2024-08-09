public void cancel( final SpiceRequest< ? > request ) {
        executorService.execute( new Runnable() {
            public void run() {
                request.cancel();
            }
        } );
    }