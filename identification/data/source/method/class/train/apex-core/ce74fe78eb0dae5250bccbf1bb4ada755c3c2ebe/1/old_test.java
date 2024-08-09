  @Before
  public void setupTest()
  {
    port = new DefaultOutputPort<>();
    sink = new Sink<Object>()
    {
      private volatile int count = 0;

      @Override
      public void put(Object tuple)
      {
        count++;
      }

      @Override
      public int getCount(boolean reset)
      {
        return count;
      }
    };
    port.setSink(sink);
  }