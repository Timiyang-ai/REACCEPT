private static void put(String key) {
    Region r = cache.getRegion(REGION_NAME);
    DeltaTestImpl val = null;
    for (int i = 0; i < NO_PUT_OPERATION; i++) {
      switch (i) {
        case 0:
          val = new DeltaTestImpl(0, "0", new Double(0), new byte[0], new TestObject1("0", 0));
          break;
        case 1:
          val = new DeltaTestImpl(0, "0", new Double(0), new byte[0], new TestObject1("0", 0));
          val.setStr((String) putDelta[0]);
          break;
        case 2:
          val = new DeltaTestImpl(0, (String) putDelta[1], new Double(0), new byte[0],
              new TestObject1("0", 0));
          val.setIntVar(((Integer) putDelta[2]).intValue());
          break;
      }
      r.put(key, val);
    }
  }