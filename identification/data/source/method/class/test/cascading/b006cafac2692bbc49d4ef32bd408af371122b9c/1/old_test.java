@Test
  public final void testStart()
    {
    Map<String, Double> context = new HashMap<String, Double>();
    average.start( context, null );

    TupleListCollector resultEntryCollector = new TupleListCollector( new Fields( "field" ) );
    average.complete( context, resultEntryCollector );
    Tuple tuple = resultEntryCollector.iterator().next();

    assertEquals( "Got expected initial value on start", 0.0, tuple.getDouble( 0 ), 0.0d );
    }