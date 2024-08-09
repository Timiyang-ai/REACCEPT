public void add( @Nonnull T listener )
    {
        requireNonNull( listener, "added listener can't be null" );

        listeners.add( listener );
    }