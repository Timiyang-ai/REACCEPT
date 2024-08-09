@Test public void testAdaptTo() {
    Scope.enter();
    Frame v1=null, v2=null;
    try {
      v1 = parse_test_file("smalldata/junit/mixcat_train.csv");
      v2 = parse_test_file("smalldata/junit/mixcat_test.csv");
      CategoricalWrappedVec vv = (CategoricalWrappedVec) v2.vecs()[0].adaptTo(v1.vecs()[0].domain());
      Assert.assertArrayEquals("Mapping differs",new int[]{0,1,3},vv._map);
      Assert.assertArrayEquals("Mapping differs",new String[]{"A","B","C","D"},vv.domain());
      vv.remove();
    } finally {
      if( v1!=null ) v1.delete();
      if( v2!=null ) v2.delete();
      Scope.exit();
    }
  }