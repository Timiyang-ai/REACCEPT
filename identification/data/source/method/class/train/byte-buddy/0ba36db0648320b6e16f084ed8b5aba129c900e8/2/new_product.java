@Setup
    public void setUp() throws Exception {
        ClassByExtensionBenchmark classByExtensionBenchmark = new ClassByExtensionBenchmark();
        baselineInstance = classByExtensionBenchmark.baseline();
        byteBuddyWithAnnotationsInstance = classByExtensionBenchmark.benchmarkByteBuddyWithAnnotations();
        byteBuddySpecializedInstance = classByExtensionBenchmark.benchmarkByteBuddySpecialized();
        cglibInstance = classByExtensionBenchmark.benchmarkCglib();
        javassistInstance = classByExtensionBenchmark.benchmarkJavassist();
    }