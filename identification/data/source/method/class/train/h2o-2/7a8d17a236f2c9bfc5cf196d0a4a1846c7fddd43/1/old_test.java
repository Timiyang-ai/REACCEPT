@Test public void testParse() {
    //File file = TestUtil.find_test_file("./smalldata/airlines/allyears2k_headers.zip");
    File file = TestUtil.find_test_file("./smalldata/logreg/prostate_long.csv.gz");
    Key fkey = NFSFileVec.make(file);

    Key okey = Key.make("cars.hex");
    ParseDataset2.parse(okey,new Key[]{fkey});

    for( int i=0; i<9; i++ )
      UKV.remove(fkey.toString()+"C"+i);
    UKV.remove(fkey);
  }