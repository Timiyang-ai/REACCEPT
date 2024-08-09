@Test
public void sortWithCustomComparisonAndNaNHandling() {
    // Test sorting with a custom comparison function that converts strings to numbers where possible.
    // This includes handling of strings that represent numbers and ensuring NaN values are sorted correctly.
    String customSortQuery = SORT.args(
        "('9','NaN','29','310','75','85','NaN','68-69','93','72','185',"
        + "'188','86','87','83','79','82','71','67','63','58','57','53','31','26','22','21','20',"
        + "'15','10','03','05','1','NaN')", 
        "function($s) { number($s) }"
    );
    String expectedResult = "'NaN'\n'NaN'\n'NaN'\n'1'\n'03'\n'05'\n'9'\n'10'\n'15'\n'20'\n'21'\n'22'"
        + "\n'26'\n'29'\n'31'\n'53'\n'57'\n'58'\n'63'\n'67'\n'68-69'\n'71'\n'72'\n'75'\n'79'\n'82'"
        + "\n'83'\n'85'\n'86'\n'87'\n'93'\n'185'\n'188'\n'310'";
    array(customSortQuery, expectedResult);
    
    // Additionally, test sorting of a simple numeric array including NaN values to verify they are handled as per the new logic.
    String numericArrayWithNaN = SORT.args(" [3, NaN, 1, 4, NaN, 2]");
    String expectedNumericResult = "[1, 2, 3, 4, NaN, NaN]";
    array(numericArrayWithNaN, expectedNumericResult);
}