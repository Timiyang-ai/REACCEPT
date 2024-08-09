@Test
    public void testGetChangeLog() throws ConnectionException {

        LOG.info( "getChangeLog" );

        // create an entity and make a series of changes to it so that versions get created
        CollectionScope context = new CollectionScopeImpl(
                new SimpleId( "organization" ), new SimpleId( "test" ), "test" );

        // Create an entity and change it's fields three times 
        EntityCollectionManager manager = factory.createCollectionManager( context );
        Entity e0 = new Entity( new SimpleId( "test" ) );
        e0.setField( new StringField( "waffleType", "belgian" ) );
        e0.setField( new IntegerField( "waffleIndex", 3 ) );
        e0.setField( new BooleanField( "syrup", false ) );
        Observable<Entity> o0 = manager.write( e0 );
        e0 = o0.toBlockingObservable().lastOrDefault( null );

        Entity e1 = manager.load( e0.getId() ).toBlockingObservable().lastOrDefault( null );
        e1.setField( new StringField( "waffleType", "belgian" ) );
        e1.setField( new IntegerField( "waffleIndex", 4 ) );
        e1.setField( new BooleanField( "syrup", true ) );
        e1.setField( new BooleanField( "butter", true ) );
        Observable<Entity> o1 = manager.write( e1 );
        e1 = o1.toBlockingObservable().lastOrDefault( null );

        Entity e2 = manager.load( e0.getId() ).toBlockingObservable().lastOrDefault( null );
        e2.setField( new StringField( "waffleType", "belgian" ) );
        e2.setField( new IntegerField( "waffleIndex", 6 ) );
        e2.setField( new BooleanField( "syrup", true ) );
        e2.setField( new BooleanField( "butter", true ) );
        e2.setField( new BooleanField( "chocolateChips", true ) );
        e2.setField( new BooleanField( "whippedCream", true ) );
        Observable<Entity> o2 = manager.write( e2 );
        e2 = o2.toBlockingObservable().lastOrDefault( null );

        Entity e3 = manager.load( e0.getId() ).toBlockingObservable().lastOrDefault( null );
        e3.setField( new StringField( "waffleType", "belgian" ) );
        e3.setField( new IntegerField( "waffleIndex", 4 ) );
        e3.setField( new BooleanField( "syrup", false ) );
        e3.setField( new BooleanField( "butter", false ) );
        Observable<Entity> o3 = manager.write( e3 );
        e3 = o3.toBlockingObservable().lastOrDefault( null );

        List<MvccEntity> versions = mvccEntitySerializationStrategy
           .load( context, e0.getId(), e3.getVersion(), 10);
        assertEquals(4, versions.size() );

        ChangeLogGeneratorImpl instance = new ChangeLogGeneratorImpl();
        List<ChangeLogEntry> result = instance.getChangeLog( versions, e2.getVersion() );

        for (ChangeLogEntry cle : result) {
            LOG.info( cle.toString() );
        }
        assertEquals(16, result.size());
    }