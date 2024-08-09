@Test public void testParse() {
    //File file = TestUtil.find_test_file("./smalldata/airlines/allyears2k_headers.zip");
    File file = TestUtil.find_test_file("./smalldata/logreg/prostate_long.csv.gz");
    Key fkey = NFSFileVec.make(file);

    Key okey = Key.make("cars.hex");
    Frame fr = ParseDataset2.parse(okey,new Key[]{fkey});
    //System.out.println(fr);
    UKV.remove(fkey);
    UKV.remove(okey);
  }