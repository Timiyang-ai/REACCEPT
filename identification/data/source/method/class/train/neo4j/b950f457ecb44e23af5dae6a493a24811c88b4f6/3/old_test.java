    @Test
    public void add()
    {
        Listener[] listenersArray = {new Listener(), new Listener(), new Listener()};

        Listeners<Listener> listeners = newListeners( listenersArray );

        assertArrayEquals( listenersArray, Iterables.asArray( Listener.class, listeners ) );
    }