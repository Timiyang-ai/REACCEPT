@TestInfo(
            level = TestLevel.PARTIAL_OK,
            purpose = "Verifies the basic functionality of end() method. ",
            targets = { @TestTarget(methodName = "end", 
                                    methodArgs = {})                         
            }
    )        
    public void test_end() {
        String testPattern = "(abb)";
        String testString = "cccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);
        int start = 3;
        int end = 6;
        int j;

        for (j = 0; j < 3; j++) {
            while (mat.find()) {
                assertEquals("Start is wrong", end, mat.end());
               
                start = end;
                end += 3;
            }
        }
    }