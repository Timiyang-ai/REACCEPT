  @Test
  public void copyOutputs() throws Exception {
    // These tests are very simple because we just rely on
    // AbstractContainerizingSandboxedSpawnTest.testMoveOutputs to properly verify all corner cases.
    Path outputFile = execRoot.getRelative("very/output.txt");

    SymlinkedSandboxedSpawn symlinkedExecRoot =
        new SymlinkedSandboxedSpawn(
            sandboxDir,
            execRoot,
            ImmutableList.of("/bin/true"),
            ImmutableMap.of(),
            new SandboxInputs(ImmutableMap.of(), ImmutableMap.of()),
            SandboxOutputs.create(
                ImmutableSet.of(outputFile.relativeTo(execRoot)), ImmutableSet.of()),
            ImmutableSet.of(),
            new SynchronousTreeDeleter(),
            /* statisticsPath= */ null);
    symlinkedExecRoot.createFileSystem();

    FileSystemUtils.createEmptyFile(outputFile);

    outputsDir.getRelative("very").createDirectory();
    symlinkedExecRoot.copyOutputs(outputsDir);

    assertThat(outputsDir.getRelative("very/output.txt").isFile(Symlinks.NOFOLLOW)).isTrue();
  }