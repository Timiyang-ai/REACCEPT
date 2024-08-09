public void cancelAllRequests() {
        executorService.execute( new Runnable() {
            public void run() {
                cancelAllRequestsInternal();
            }
        } );
    }