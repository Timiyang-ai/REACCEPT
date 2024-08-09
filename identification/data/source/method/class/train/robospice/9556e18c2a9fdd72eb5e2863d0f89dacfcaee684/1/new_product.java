public synchronized void shouldStop() {
        if ( this.runner == null ) {
            throw new IllegalStateException( "Not started yet" );
        }
        Ln.d( "Content manager stopping." );
        dontNotifyAnyRequestListenersInternal();
        isUnbinding = false;
        unbindFromService( context.get() );
        spiceServiceConnection = null;
        this.isStopped = true;
        this.runner.interrupt();
        this.runner = null;
        this.context.clear();
        Ln.d( "Content manager stopped." );
    }