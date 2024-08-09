@TestInfo(
            level = TestLevel.PARTIAL_OK,
            purpose = "Verifies the start() method.",
            targets = { @TestTarget(methodName = "start", 
                                    methodArgs = {})                         
            }
    )      
    public void test_start() {
        String testPattern = "(abb)";
        String testString = "cccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);
        int start = 3;
        int end = 6;
        int j;

        for (j = 0; j < 3; j++) {
            while (mat.find()) {
                assertEquals("Start is wrong", start, mat.start());
               
                start = end;
                end += 3;
            }
        }
    }