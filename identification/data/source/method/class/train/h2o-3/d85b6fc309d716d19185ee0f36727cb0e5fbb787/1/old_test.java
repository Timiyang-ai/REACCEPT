@Test public void testSet() {
    Frame fr = null;
    try {
      fr = parse_test_file("./smalldata/airlines/allyears2k_headers.zip");
      double[] mins =new double[fr.numCols()];
      for (int i=0; i < mins.length; i++)
        mins[i] = fr.vecs()[i].min();
      // Scribble into a freshly parsed frame
      new SetDoubleInt(mins).doAll(fr);
    } finally {
      if( fr != null ) fr.delete();
    }
  }