public static String execCommand(String... cmd) throws IOException {
    ShellUtils exec = new ShellUtils(cmd);
    exec.run();
    return exec.getOutput();
  }