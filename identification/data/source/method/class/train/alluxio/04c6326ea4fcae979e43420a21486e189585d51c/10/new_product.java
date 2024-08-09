private void persist(TachyonURI filePath) throws IOException {
    try {
      URIStatus status = mFileSystem.getStatus(filePath);
      if (status.isFolder()) {
        List<URIStatus> statuses = mFileSystem.listStatus(filePath);
        List<String> errorMessages = new ArrayList<String>();
        for (URIStatus uriStatus : statuses) {
          TachyonURI newPath = new TachyonURI(uriStatus.getPath());
          try {
            persist(newPath);
          } catch (IOException e) {
            errorMessages.add(e.getMessage());
          }
        }
        if (errorMessages.size() != 0) {
          throw new IOException(Joiner.on('\n').join(errorMessages));
        }
      } else if (status.isPersisted()) {
        System.out.println(filePath + " is already persisted");
      } else {
        long size = FileSystemUtils.persistFile(mFileSystem, filePath, status, mTachyonConf);
        System.out.println("persisted file " + filePath + " with size " + size);
      }
    } catch (TachyonException e) {
      throw new IOException(e.getMessage());
    }
  }