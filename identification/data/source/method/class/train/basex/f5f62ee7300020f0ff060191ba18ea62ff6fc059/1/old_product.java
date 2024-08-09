public static void restore(final IOFile file, final Restore cmd, final GlobalOptions glblOptions)
      throws IOException {
    final Zip zip = new Zip(file);
    if(cmd != null) cmd.proc(zip);
    zip.unzip(glblOptions.dbpath());
  }