public static String execCommand(String... cmd) throws IOException {
    return new Command(cmd).run();
  }