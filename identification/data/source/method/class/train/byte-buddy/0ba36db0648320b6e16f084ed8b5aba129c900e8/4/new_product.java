@Setup
    public void setUp() throws Exception {
        ClassByExtensionBenchmark classByExtensionBenchmark = new ClassByExtensionBenchmark();
        baselineInstance = classByExtensionBenchmark.baseline();
        byteBuddyWithProxyInstance = classByExtensionBenchmark.benchmarkByteBuddyWithProxy();
        byteBuddyWithAccessorInstance = classByExtensionBenchmark.benchmarkByteBuddyWithAccessor();
        byteBuddyWithPrefixInstance = classByExtensionBenchmark.benchmarkByteBuddyWithPrefix();
        byteBuddySpecializedInstance = classByExtensionBenchmark.benchmarkByteBuddySpecialized();
        cglibInstance = classByExtensionBenchmark.benchmarkCglib();
        javassistInstance = classByExtensionBenchmark.benchmarkJavassist();
    }