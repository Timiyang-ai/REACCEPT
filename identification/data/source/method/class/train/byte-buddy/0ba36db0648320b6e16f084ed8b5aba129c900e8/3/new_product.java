@Setup
    public void setUp() throws Exception {
        ClassByExtensionBenchmark classByExtensionBenchmark = new ClassByExtensionBenchmark();
        baselineInstance = classByExtensionBenchmark.baseline();
        byteBuddyWithProxiesInstance = classByExtensionBenchmark.benchmarkByteBuddyWithProxies();
        byteBuddyWithAccessorsInstance = classByExtensionBenchmark.benchmarkByteBuddyWithAccessors();
        byteBuddySpecializedInstance = classByExtensionBenchmark.benchmarkByteBuddySpecialized();
        cglibInstance = classByExtensionBenchmark.benchmarkCglib();
        javassistInstance = classByExtensionBenchmark.benchmarkJavassist();
    }