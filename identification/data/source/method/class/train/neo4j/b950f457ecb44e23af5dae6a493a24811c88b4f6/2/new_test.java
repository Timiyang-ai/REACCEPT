    @Test
    public void remove()
    {
        Listener listener1 = new Listener();
        Listener listener2 = new Listener();
        Listener listener3 = new Listener();

        Listeners<Listener> listeners = newListeners( listener1, listener2, listener3 );

        assertEquals( Arrays.asList( listener1, listener2, listener3 ), Iterables.asList( listeners ) );

        listeners.remove( listener1 );
        assertEquals( Arrays.asList( listener2, listener3 ), Iterables.asList( listeners ) );

        listeners.remove( listener3 );
        assertEquals( singletonList( listener2 ), Iterables.asList( listeners ) );
    }