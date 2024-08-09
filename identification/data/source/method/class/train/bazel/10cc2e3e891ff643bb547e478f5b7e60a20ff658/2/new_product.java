@Override
  public void copyOutputs(Path execRoot) throws IOException {
    SandboxedSpawn.moveOutputs(outputs, sandboxExecRoot, execRoot);
  }