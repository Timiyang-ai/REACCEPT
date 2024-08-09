public FileStatus getFileStatus(final String path) throws IOException,
      FileNotFoundException, InterruptedException {
    return execute(new PrivilegedExceptionAction<FileStatus>() {
      public FileStatus run() throws FileNotFoundException, IOException {
        return fs.getFileStatus(new Path(path));
      }
    });
  }