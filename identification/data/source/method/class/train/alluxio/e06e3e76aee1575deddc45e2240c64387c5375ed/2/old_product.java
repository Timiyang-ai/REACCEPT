public static String execCommand(String... cmd) throws IOException {
    ShellCommandExecutor exec = new ShellCommandExecutor(cmd);
    exec.execute();
    return exec.getOutput();
  }