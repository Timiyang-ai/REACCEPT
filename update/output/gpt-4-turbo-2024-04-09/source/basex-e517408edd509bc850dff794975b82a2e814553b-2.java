@Test
public void sort() {
    // Test basic sorting with integers
    array(_ARRAY_SORT.args("[1, 4, 6, 5, 3]"), "[1, 3, 4, 5, 6]");
    // Test sorting with tuples
    array(_ARRAY_SORT.args("[(1,0), (1,1), (0,1), (0,0)]"), "[(0, 0), (0, 1), (1, 0), (1, 1)]");
    // Test sorting with absolute values
    array(_ARRAY_SORT.args("[1, -2, 5, 10, -10, 10, 8]", " abs#1"), "[1, -2, 5, 8, 10, -10, 10]");
    // Test sorting with null values and non-comparable types
    array(_ARRAY_SORT.args("[1, null, 3, 'text', 2]"), "[null, 1, 2, 3, 'text']");
    // Test sorting with floating point numbers and NaN values
    array(_ARRAY_SORT.args("[1.5, NaN, 2.5, NaN, 3.5]"), "[NaN, NaN, 1.5, 2.5, 3.5]");
    // Test sorting with a complex function that handles different types
    array(_ARRAY_SORT.args("['apple', 'banana', null, 'cherry']", "function($a, $b) { if ($a == null) return 1; if ($b == null) return -1; $a <=> $b }"), "[null, 'apple', 'banana', 'cherry']");
}