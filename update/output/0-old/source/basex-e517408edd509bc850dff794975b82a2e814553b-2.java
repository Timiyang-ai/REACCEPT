@Test public void sort() {
    array(_ARRAY_SORT.args(" [1,4,6,5,3]"), "[1, 3, 4, 5, 6]");
    array(_ARRAY_SORT.args(" [(1,0), (1,1), (0,1), (0,0)]"), "[(0, 0), (0, 1), (1, 0), (1, 1)]");
    array(_ARRAY_SORT.args(" [1,-2,5,10,-10,10,8]", " abs#1"), "[1, -2, 5, 8, 10, -10, 10]");
  }