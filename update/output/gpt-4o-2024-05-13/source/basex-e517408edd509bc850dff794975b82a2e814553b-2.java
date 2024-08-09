@Test
public void sort() {
    // Test sorting without a custom function
    array(_ARRAY_SORT.args(" [1,4,6,5,3]"), "[1, 3, 4, 5, 6]");

    // Test sorting tuples
    array(_ARRAY_SORT.args(" [(1,0), (1,1), (0,1), (0,0)]"), "[(0, 0), (0, 1), (1, 0), (1, 1)]");

    // Test sorting with a custom function that returns absolute values
    array(_ARRAY_SORT.args(" [1,-2,5,10,-10,10,8]", " function($x) { abs($x) }"), "[1, -2, 5, 8, 10, -10, 10]");
}