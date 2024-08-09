@TestFactory
    Stream<DynamicTest> generateBulkCopyOptionsTest() {
        List<BulkCopyTestWrapper> testData = createTestData_testBulkCopyOption();
        return testData.stream().map(new Function<BulkCopyTestWrapper, DynamicTest>() {
            @Override
            public DynamicTest apply(final BulkCopyTestWrapper datum) {
                return DynamicTest.dynamicTest("Testing " + datum.testName, new org.junit.jupiter.api.function.Executable() {
                    @Override
                    public void execute() {
                        BulkCopyTestUtil.performBulkCopy(datum, sourceTable);
                    }
                });
            }
        });
    }