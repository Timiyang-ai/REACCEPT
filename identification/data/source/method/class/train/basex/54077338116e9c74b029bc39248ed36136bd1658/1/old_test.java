@Test
  public void sort() {
    query(SORT.args("(1, 4, 6, 5, 3)"), "1\n3\n4\n5\n6");
    query(SORT.args("(1,-2,5,10,-10,10,8)", " abs#1"), "1\n-2\n5\n8\n10\n-10\n10");
    query(SORT.args("((1,0), (1,1), (0,1), (0,0))"), "0\n0\n0\n0\n1\n1\n1\n1");
  }