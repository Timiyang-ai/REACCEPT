  @Test
  public void getTransitiveCxxPreprocessorInput() {
    FakeProjectFilesystem filesystem = new FakeProjectFilesystem();
    CxxPlatform cxxPlatform =
        CxxPlatformUtils.build(
            new CxxBuckConfig(FakeBuckConfig.builder().setFilesystem(filesystem).build()));

    // Setup a simple CxxPreprocessorDep which contributes components to preprocessing.
    BuildTarget cppDepTarget1 = BuildTargetFactory.newInstance("//:cpp1");
    CxxPreprocessorInput input1 =
        CxxPreprocessorInput.builder()
            .addRules(cppDepTarget1)
            .putPreprocessorFlags(CxxSource.Type.C, StringArg.of("-Dtest=yes"))
            .putPreprocessorFlags(CxxSource.Type.CXX, StringArg.of("-Dtest=yes"))
            .build();
    BuildTarget depTarget1 = BuildTargetFactory.newInstance("//:dep1");
    FakeCxxPreprocessorDep dep1 = createFakeCxxPreprocessorDep(depTarget1, input1);

    // Setup another simple CxxPreprocessorDep which contributes components to preprocessing.
    BuildTarget cppDepTarget2 = BuildTargetFactory.newInstance("//:cpp2");
    CxxPreprocessorInput input2 =
        CxxPreprocessorInput.builder()
            .addRules(cppDepTarget2)
            .putPreprocessorFlags(CxxSource.Type.C, StringArg.of("-DBLAH"))
            .putPreprocessorFlags(CxxSource.Type.CXX, StringArg.of("-DBLAH"))
            .build();
    BuildTarget depTarget2 = BuildTargetFactory.newInstance("//:dep2");
    FakeCxxPreprocessorDep dep2 = createFakeCxxPreprocessorDep(depTarget2, input2);

    // Create a normal dep which depends on the two CxxPreprocessorDep rules above.
    BuildTarget depTarget3 = BuildTargetFactory.newInstance("//:dep3");
    CxxPreprocessorInput nothing = CxxPreprocessorInput.of();
    FakeCxxPreprocessorDep dep3 = createFakeCxxPreprocessorDep(depTarget3, nothing, dep1, dep2);

    // Verify that getTransitiveCxxPreprocessorInput gets all CxxPreprocessorInput objects
    // from the relevant rules above.
    ImmutableList<CxxPreprocessorInput> expected = ImmutableList.of(nothing, input1, input2);
    ImmutableList<CxxPreprocessorInput> actual =
        ImmutableList.copyOf(
            CxxPreprocessables.getTransitiveCxxPreprocessorInput(
                cxxPlatform, new TestActionGraphBuilder(), ImmutableList.of(dep3)));
    assertEquals(expected, actual);
  }