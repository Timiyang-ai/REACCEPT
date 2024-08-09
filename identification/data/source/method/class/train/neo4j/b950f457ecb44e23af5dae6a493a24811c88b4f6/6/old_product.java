public void remove( @Nonnull T listener )
    {
        requireNonNull( listener, "removed listener can't be null" );

        listeners.remove( listener );
    }