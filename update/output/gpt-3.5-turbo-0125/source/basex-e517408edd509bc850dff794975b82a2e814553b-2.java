@Test
public void sort() {
    array(_ARRAY_SORT.args(" [1,4,6,5,3]"), "[1, 3, 4, 5, 6]");
    array(_ARRAY_SORT.args(" [(1,0), (1,1), (0,1), (0,0)]"), "[(0, 0), (0, 1), (1, 0), (1, 1)");
    array(_ARRAY_SORT.args(" [1,-2,5,10,-10,10,8]", " abs#1"), "[1, -2, 5, 8, 10, -10, 10]");
    query(COUNT.args(SORT.args("('9','8','29','310','75','85','36-37','68-69','93','72','185',"
        + "'188','86','87','83','79','82','71','67','63','58','57','53','31','26','22','21','20'"
        + ",'15','10','03','05','1')", "function($s) { number($s) }")),
        "33");
}