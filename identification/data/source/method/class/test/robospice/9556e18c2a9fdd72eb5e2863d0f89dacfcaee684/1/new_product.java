public synchronized void shouldStop() {
        if ( this.runner == null ) {
            throw new IllegalStateException( "Not started yet" );
        }
        Ln.d( "SpiceManager stopping." );
        dontNotifyAnyRequestListenersInternal();
        isUnbinding = false;
        unbindFromService( context.get() );
        spiceServiceConnection = null;
        this.isStopped = true;
        this.runner.interrupt();
        this.runner = null;
        this.context.clear();
        Ln.d( "SpiceManager stopped." );
    }