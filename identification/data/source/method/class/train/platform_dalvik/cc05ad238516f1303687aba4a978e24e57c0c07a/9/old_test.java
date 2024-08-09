@TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "clear",
          methodArgs = {}
        )
    })
    public void test_clear() {
        // Test for method void java.util.TreeSet.clear()
        ts.clear();
        assertEquals("Returned non-zero size after clear", 0, ts.size());
        assertTrue("Found element in cleared set", !ts.contains(objArray[0]));
    }