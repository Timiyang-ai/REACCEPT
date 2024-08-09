  @Test public void makeCopy() {
    Vec copyOfVec = null;
    Vec expected = null;
    try {
      Scope.enter();
      Vec originalVec = vec(1, 2, 3, 4, 5);
      copyOfVec = originalVec.makeCopy();
      Scope.untrack(copyOfVec._key);
      Scope.exit();
      expected = vec(1, 2, 3, 4, 5);
      assertVecEquals(expected, copyOfVec, 1e-5);
    } finally {
      if( copyOfVec !=null ) copyOfVec.remove();
      if( expected !=null ) expected.remove();
    }

  }