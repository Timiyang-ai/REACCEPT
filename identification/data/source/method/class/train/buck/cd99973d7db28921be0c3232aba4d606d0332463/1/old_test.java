@Test
  public void testGetAndroidResourceDeps() {
    BuildRuleResolver ruleResolver = new BuildRuleResolver();
    BuildRule c = ruleResolver.addToIndex(
        AndroidResourceRuleBuilder.newBuilder()
            .setBuildTarget(BuildTargetFactory.newInstance("//:c"))
            .setRes(Paths.get("res_c"))
            .setRDotJavaPackage("com.facebook")
            .build());

    BuildRule b = ruleResolver.addToIndex(
        AndroidResourceRuleBuilder.newBuilder()
            .setBuildTarget(BuildTargetFactory.newInstance("//:b"))
            .setRes(Paths.get("res_b"))
            .setRDotJavaPackage("com.facebook")
            .setDeps(ImmutableSortedSet.of(c))
            .build());

    BuildRule d = ruleResolver.addToIndex(
        AndroidResourceRuleBuilder.newBuilder()
            .setBuildTarget(BuildTargetFactory.newInstance("//:d"))
            .setRes(Paths.get("res_d"))
            .setRDotJavaPackage("com.facebook")
            .setDeps(ImmutableSortedSet.of(c))
            .build());

    BuildRule a = ruleResolver.addToIndex(
        AndroidResourceRuleBuilder.newBuilder()
            .setBuildTarget(BuildTargetFactory.newInstance("//:a"))
            .setRes(Paths.get("res_a"))
            .setRDotJavaPackage("com.facebook")
            .setDeps(ImmutableSortedSet.of(b, c, d))
            .build());

    AndroidPackageableCollector collector = new AndroidPackageableCollector();
    collector.addPackageables(ImmutableList.of(((AndroidPackageable) a.getBuildable())));

    // Note that a topological sort for a DAG is not guaranteed to be unique, but we order nodes
    // within the same depth of the search.
    ImmutableList<BuildTarget> result = FluentIterable.from(ImmutableList.of(a, d, b, c))
        .transform(BuildTarget.TO_TARGET)
        .toList();

    assertEquals(
        String.format("Android resources should be topologically sorted."),
        result,
        collector.build().resourceDetails.resourcesWithNonEmptyResDir);

    // Introduce an AndroidBinaryRule that depends on A and C and verify that the same topological
    // sort results. This verifies that both AndroidResourceRule.getAndroidResourceDeps does the
    // right thing when it gets a non-AndroidResourceRule as well as an AndroidResourceRule.
    BuildTarget keystoreTarget = BuildTargetFactory.newInstance("//keystore:debug");
    BuildRule keystore = KeystoreBuilder.createBuilder(keystoreTarget)
        .setStore(Paths.get("keystore/debug.keystore"))
        .setProperties(Paths.get("keystore/debug.keystore.properties"))
        .build(ruleResolver);

    ImmutableSortedSet<BuildRule> declaredDeps = ImmutableSortedSet.of(a, c);
    BuildRule e = AndroidBinaryBuilder.newBuilder()
        .setBuildTarget(BuildTargetFactory.newInstance("//:e"))
        .setManifest(new TestSourcePath("AndroidManfiest.xml"))
        .setTarget("Google Inc.:Google APIs:16")
        .setKeystore((Keystore) keystore.getBuildable())
        .setOriginalDeps(declaredDeps)
        .build(ruleResolver);
    AndroidBinary androidBinary = ((AndroidBinary) e.getBuildable());
    androidBinary.getEnhancedDeps(
        ruleResolver,
        declaredDeps,
        ImmutableSortedSet.of(keystore));

    assertEquals(
        String.format("Android resources should be topologically sorted."),
        result,
        androidBinary
            .getAndroidPackageableCollection()
            .resourceDetails
            .resourcesWithNonEmptyResDir);
  }