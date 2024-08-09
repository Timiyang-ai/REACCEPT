@Deprecated
    public void add( T listener )
    {
        requireNonNull( listener, "added listener can't be null" );

        listeners.add( listener );
    }