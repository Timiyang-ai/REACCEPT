@Test
  public final void testStart()
    {
    Map<String, Double> context = new HashMap<String, Double>();
    min.start( context, null );
    TupleEntryCollector resultEntryCollector = new TupleEntryCollector( new Fields( "field" ) );
    min.complete( context, resultEntryCollector.iterator() );
    Tuple tuple = resultEntryCollector.iterator().next().getTuple();

    assertEquals( "Got expected initial value on start", null, tuple.get( 0 ) );
    }