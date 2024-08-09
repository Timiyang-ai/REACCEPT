@Test
  public final void testStart()
    {
    Map<String, Double> context = new HashMap<String, Double>();
    average.start( context );

    TupleEntryCollector resultEntryCollector = new TupleEntryCollector( new Fields( "field" ) );
    average.complete( context, resultEntryCollector.iterator() );
    Tuple tuple = resultEntryCollector.iterator().next().getTuple();

    assertEquals( "Got expected initial value on start", 0.0, tuple.getDouble( 0 ), 0.0d );
    }