  @Test
  public void provideGeneratedComponentDirs_valid() {
    BuildInfoProvider[] providers = {(dir, packageName) -> "app/com/test/example"};
    VirtualFile[] mocks = createPathMocks("app", "com", "test", "example");
    Project mockProject = mock(Project.class);
    MockPsiManager mockPsiManager = new MockPsiManager(mockProject);
    PsiDirectory mockDirectory = mock(PsiDirectory.class);
    mockPsiManager.addPsiDirectory(mocks[1], mockDirectory);

    PsiDirectory found =
        ComponentBuildInfoProvider.provideGeneratedComponentDirs(
                providers, "com/test/example", "com.test.example", mocks[0], mockPsiManager)
            .findFirst()
            .get();

    assertEquals(mockDirectory, found);
  }