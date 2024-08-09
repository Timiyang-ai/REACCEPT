@Test
public void sort() {
    // Adjusted test calls to include the new Collation parameter as per the production method change
    query(SORT.args("(1, 4, 6, 5, 3)", "()"), "1\n3\n4\n5\n6");
    query(SORT.args("(1,-2,5,10,-10,10,8)", "()", " abs#1"), "1\n-2\n5\n8\n10\n-10\n10");
    query(SORT.args("((1,0), (1,1), (0,1), (0,0))", "()"), "0\n0\n0\n0\n1\n1\n1\n1");
    
    // Updated test cases to reflect the change in how the sorting function is expected to be called
    // and adjusted the expected result based on the new sorting behavior.
    query(SORT.args("('9','8','29','310','75','85','36-37','93','72','185','188','86','87','83',"
        + "'79','82','71','67','63','58','57','53','31','26','22','21','20','15','10')", "()",
        "function($s) { number($s) }") + "[1]",
        "36-37");
    query(SORT.args("(1,2)", "()", "function($s) { [$s] }"), "1\n2");
}