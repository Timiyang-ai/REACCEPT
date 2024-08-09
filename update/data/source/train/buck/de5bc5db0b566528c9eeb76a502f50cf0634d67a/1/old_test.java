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

    ImmutableList<HasAndroidResourceDeps> deps = UberRDotJavaUtil.getAndroidResourceDeps(a);

    Function<BuildRule, Buildable> ruleToBuildable = new Function<BuildRule, Buildable>() {
      @Override
      public Buildable apply(BuildRule input) {
        return input.getBuildable();
      }
    };
    // Note that a topological sort for a DAG is not guaranteed to be unique. In this particular
    // case, there are two possible valid outcomes.
    ImmutableList<Buildable> validResult1 = FluentIterable.from(ImmutableList.of(a, b, d, c))
        .transform(ruleToBuildable)
        .toList();
    ImmutableList<Buildable> validResult2 = FluentIterable.from(ImmutableList.of(a, d, b, c))
        .transform(ruleToBuildable)
        .toList();

    assertTrue(
        String.format(
            "Topological sort %s should be either %s or %s", deps, validResult1, validResult2),
        deps.equals(validResult1) || deps.equals(validResult2));

    // Introduce an AndroidBinaryRule that depends on A and C and verify that the same topological
    // sort results. This verifies that both AndroidResourceRule.getAndroidResourceDeps does the
    // right thing when it gets a non-AndroidResourceRule as well as an AndroidResourceRule.
    BuildTarget keystoreTarget = BuildTargetFactory.newInstance("//keystore:debug");
    Keystore keystore = (Keystore) KeystoreBuilder.createBuilder(keystoreTarget)
        .setStore(Paths.get("keystore/debug.keystore"))
        .setProperties(Paths.get("keystore/debug.keystore.properties"))
        .build(ruleResolver)
        .getBuildable();

    BuildRule e = AndroidBinaryBuilder.newBuilder()
            .setBuildTarget(BuildTargetFactory.newInstance("//:e"))
            .setManifest(new TestSourcePath("AndroidManfiest.xml"))
            .setTarget("Google Inc.:Google APIs:16")
            .setKeystore(keystore)
            .setOriginalDeps(ImmutableSortedSet.of(a, c))
            .build(ruleResolver);
    e.getBuildable().getEnhancedDeps(ruleResolver);

    ImmutableList<HasAndroidResourceDeps> deps2 = UberRDotJavaUtil.getAndroidResourceDeps(e);
    assertTrue(
        String.format(
            "Topological sort %s should be either %s or %s", deps, validResult1, validResult2),
            deps2.equals(validResult1) || deps2.equals(validResult2));
  }