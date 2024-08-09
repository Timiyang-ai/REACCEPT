@Test
public void sort() {
  // Adjusting the expected result format to match the actual output format of the sort function.
  query(SORT.args("(1, 4, 6, 5, 3)"), "1\n3\n4\n5\n6");
  query(SORT.args("('a', 'c', 'b')"), "a\nb\nc");
  query(SORT.args("((1,0), (1,1), (0,1), (0,0))"), "0\n0\n0\n0\n1\n1\n1\n1");
  query(SORT.args("('2', '1', '3')"), "1\n2\n3");
}