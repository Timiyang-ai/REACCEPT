@Test
  public void testGetAndroidResourceDeps() {
    BuildRuleResolver ruleResolver = new BuildRuleResolver();
    AndroidResourceRule c = ruleResolver.buildAndAddToIndex(
        AndroidResourceRule.newAndroidResourceRuleBuilder(new FakeAbstractBuildRuleBuilderParams())
            .setBuildTarget(BuildTargetFactory.newInstance("//:c"))
            .setRes("res_c")
            .setRDotJavaPackage("com.facebook"));

    AndroidResourceRule b = ruleResolver.buildAndAddToIndex(
        AndroidResourceRule.newAndroidResourceRuleBuilder(new FakeAbstractBuildRuleBuilderParams())
        .setBuildTarget(BuildTargetFactory.newInstance("//:b"))
        .setRes("res_b")
        .setRDotJavaPackage("com.facebook")
        .addDep(BuildTargetFactory.newInstance("//:c")));

    AndroidResourceRule d = ruleResolver.buildAndAddToIndex(
        AndroidResourceRule.newAndroidResourceRuleBuilder(new FakeAbstractBuildRuleBuilderParams())
            .setBuildTarget(BuildTargetFactory.newInstance("//:d"))
            .setRes("res_d")
            .setRDotJavaPackage("com.facebook")
            .addDep(BuildTargetFactory.newInstance("//:c")));

    AndroidResourceRule a = ruleResolver.buildAndAddToIndex(
        AndroidResourceRule.newAndroidResourceRuleBuilder(new FakeAbstractBuildRuleBuilderParams())
        .setBuildTarget(BuildTargetFactory.newInstance("//:a"))
        .setRes("res_a")
        .setRDotJavaPackage("com.facebook")
        .addDep(BuildTargetFactory.newInstance("//:b"))
        .addDep(BuildTargetFactory.newInstance("//:c"))
        .addDep(BuildTargetFactory.newInstance("//:d")));

    DependencyGraph graph = RuleMap.createGraphFromBuildRules(ruleResolver);
    ImmutableList<HasAndroidResourceDeps> deps = UberRDotJavaUtil.getAndroidResourceDeps(a, graph);

    // Note that a topological sort for a DAG is not guaranteed to be unique. In this particular
    // case, there are two possible valid outcomes.
    ImmutableList<AndroidResourceRule> validResult1 = ImmutableList.of(a, b, d, c);
    ImmutableList<AndroidResourceRule> validResult2 = ImmutableList.of(a, d, b, c);

    assertTrue(
        String.format(
            "Topological sort %s should be either %s or %s", deps, validResult1, validResult2),
        deps.equals(validResult1) || deps.equals(validResult2));

    // Introduce an AndroidBinaryRule that depends on A and C and verify that the same topological
    // sort results. This verifies that both AndroidResourceRule.getAndroidResourceDeps does the
    // right thing when it gets a non-AndroidResourceRule as well as an AndroidResourceRule.
    BuildTarget keystoreTarget = BuildTargetFactory.newInstance("//keystore:debug");
    ruleResolver.buildAndAddToIndex(
        Keystore.newKeystoreBuilder(new FakeAbstractBuildRuleBuilderParams())
        .setBuildTarget(keystoreTarget)
        .setStore("keystore/debug.keystore")
        .setProperties("keystore/debug.keystore.properties")
        .addVisibilityPattern(BuildTargetPattern.MATCH_ALL));
    AndroidBinaryRule e = ruleResolver.buildAndAddToIndex(
        AndroidBinaryRule.newAndroidBinaryRuleBuilder(new FakeAbstractBuildRuleBuilderParams())
        .setBuildTarget(BuildTargetFactory.newInstance("//:e"))
        .setManifest(new FileSourcePath("AndroidManfiest.xml"))
        .setTarget("Google Inc.:Google APIs:16")
        .setKeystore(keystoreTarget)
        .addDep(BuildTargetFactory.newInstance("//:a"))
        .addDep(BuildTargetFactory.newInstance("//:c")));

    DependencyGraph graph2 = RuleMap.createGraphFromBuildRules(ruleResolver);
    ImmutableList<HasAndroidResourceDeps> deps2 = UberRDotJavaUtil.getAndroidResourceDeps(e, graph2);
    assertTrue(
        String.format(
            "Topological sort %s should be either %s or %s", deps, validResult1, validResult2),
            deps2.equals(validResult1) || deps2.equals(validResult2));
  }