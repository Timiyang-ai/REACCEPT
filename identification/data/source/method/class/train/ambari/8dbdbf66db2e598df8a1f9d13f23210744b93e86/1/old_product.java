public FileStatus getFileStatus(final String path) throws IOException,
      FileNotFoundException, InterruptedException {
    return ugi.doAs(new PrivilegedExceptionAction<FileStatus>() {
      public FileStatus run() throws FileNotFoundException, IOException {
        return fs.getFileStatus(new Path(path));
      }
    });
  }