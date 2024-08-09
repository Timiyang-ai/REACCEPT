  @Test
  public void setLocalDirStickyBit() throws IOException {
    File tempFolder = mTestFolder.newFolder("dirToModify");
    // Only test this functionality of the absolute path of the temporary directory starts with "/",
    // which implies the host should support "chmod".
    if (tempFolder.getAbsolutePath().startsWith(AlluxioURI.SEPARATOR)) {
      FileUtils.setLocalDirStickyBit(tempFolder.getAbsolutePath());
      List<String> commands = new ArrayList<>();
      commands.add("/bin/ls");
      commands.add("-ld");
      commands.add(tempFolder.getAbsolutePath());
      try {
        ProcessBuilder builder = new ProcessBuilder(commands);
        Process process = builder.start();
        process.waitFor();
        BufferedReader stdInput = new BufferedReader(new
            InputStreamReader(process.getInputStream()));
        String line = stdInput.readLine();
        // we are just concerned about the first and the last permission bits
        assertTrue(line.matches("^d[rwx-]{8}t.*$"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }