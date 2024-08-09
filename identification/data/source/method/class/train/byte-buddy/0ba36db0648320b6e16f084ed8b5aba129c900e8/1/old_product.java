@Setup
    public void setUp() throws Exception {
        ClassByImplementationBenchmark classByImplementationBenchmark = new ClassByImplementationBenchmark();
        byteBuddyInstance = classByImplementationBenchmark.benchmarkByteBuddy();
        cglibInstance = classByImplementationBenchmark.benchmarkCglib();
        javassistInstance = classByImplementationBenchmark.benchmarkJavassist();
        jdkProxyInstance = classByImplementationBenchmark.benchmarkJdkProxy();
    }