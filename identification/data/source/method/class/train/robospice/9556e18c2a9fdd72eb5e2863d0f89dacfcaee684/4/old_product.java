public synchronized void shouldStop() {
        if ( this.runner == null ) {
            throw new IllegalStateException( "Not started yet" );
        }
        Log.d( LOG_TAG, "Content manager stopping." );
        dontNotifyAnyRequestListenersInternal();
        isUnbinding = false;
        unbindFromService( context.get() );
        contentServiceConnection = null;
        this.isStopped = true;
        this.runner = null;
        this.context.clear();
        Log.d( LOG_TAG, "Content manager stopped." );
    }