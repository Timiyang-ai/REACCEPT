@Test public void testParse2() {
    File file = find_test_file("../smalldata/junit/syn_2659x1049.csv.gz");
    NFSFileVec nfs = NFSFileVec.make(file);
    Frame fr = null;
    Vec vz = null;
    try {
      fr = ParseDataset2.parse(Key.make("syn.hex"),nfs._key);
      assertEquals(fr.numCols(),1050); // Count of columns
      assertEquals(fr.numRows(),2659); // Count of rows

      double[] sums = new Sum().doAll(fr)._sums;
      assertEquals(3949,sums[0],EPSILON);
      assertEquals(3986,sums[1],EPSILON);
      assertEquals(3993,sums[2],EPSILON);

      // Create a temp column of zeros
      Vec v0 = fr.vecs()[0];
      Vec v1 = fr.vecs()[1];
      vz = v0.makeZero();
      // Add column 0 & 1 into the temp column
      new PairSum().doAll(vz,v0,v1);
      // Add the temp to frame
      // Now total the temp col
      fr.delete();              // Remove all other columns
      fr = new Frame(new String[]{"tmp"},new Vec[]{vz}); // Add just this one
      sums = new Sum().doAll(fr)._sums;
      assertEquals(3949+3986,sums[0],EPSILON);

    } finally {
      nfs.remove();
      if( vz != null ) vz.remove();
      if( fr != null ) fr.delete();
    }
  }