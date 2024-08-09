@Test
    public void testGetChangeLog2() throws ConnectionException {

        LOG.info( "getChangeLog2" );

        // create an entity and make a series of changes to it so that versions get created
        CollectionScope context = new CollectionScopeImpl(
                new SimpleId( "organization" ), new SimpleId( "test" ), "test" );

        // V1 : { "name" : "name1" , "count": 1}
        // V2:  { "name" : "name2" , "count": 2, "nickname" : "buddy"}
        // V3:  { "name" : "name3" , "count": 2}
        
        EntityCollectionManager manager = factory.createCollectionManager( context );
        Entity e1 = new Entity( new SimpleId( "test" ) );
        e1.setField( new StringField( "name", "name1" ) );
        e1.setField( new IntegerField( "count", 1 ) );
        Observable<Entity> o1 = manager.write( e1 );
        e1 = o1.toBlockingObservable().lastOrDefault( null );

        Entity e2 = manager.load( e1.getId() ).toBlockingObservable().lastOrDefault( null );
        e2.setField( new StringField( "name", "name2" ) );
        e2.setField( new IntegerField( "count", 2 ) );
        e2.setField( new StringField( "nickname", "buddy" ) );
        Observable<Entity> o2 = manager.write( e2 );
        e2 = o2.toBlockingObservable().lastOrDefault( null );

        Entity e3 = manager.load( e1.getId() ).toBlockingObservable().lastOrDefault( null );
        e3.setField( new StringField( "name", "name3" ) );
        e3.setField( new IntegerField( "count", 2 ) );
        //e3.setField( new StringField( "nickname", null )); // why does this not work?
        e3.getFields().remove(new StringField( "nickname", "buddy"));
        Observable<Entity> o3 = manager.write( e3 );
        e3 = o3.toBlockingObservable().lastOrDefault( null );

        {
            List<MvccEntity> versions = mvccEntitySerializationStrategy
               .load( context, e1.getId(), e3.getVersion(), 10);

            ChangeLogGeneratorImpl instance = new ChangeLogGeneratorImpl();
            List<ChangeLogEntry> result = instance.getChangeLog( versions, e3.getVersion() );

            for (ChangeLogEntry cle : result) {
                LOG.info( cle.toString() );
            }
            assertEquals(7, result.size() );
        }
       
        LOG.info("===================");

        {
            List<MvccEntity> versions = mvccEntitySerializationStrategy
               .load( context, e1.getId(), e3.getVersion(), 10);

            ChangeLogGeneratorImpl instance = new ChangeLogGeneratorImpl();
            List<ChangeLogEntry> result = instance.getChangeLog( versions, e2.getVersion() );

            for (ChangeLogEntry cle : result) {
                LOG.info( cle.toString() );
            }
            assertEquals(6, result.size() );
        }
    }