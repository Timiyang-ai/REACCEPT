private void persist(TachyonURI filePath) throws IOException {
    try {
      TachyonFile fd = mTfs.open(filePath);
      FileInfo fInfo = mTfs.getInfo(fd);
      if (fInfo.isIsFolder()) {
        List<FileInfo> files = mTfs.listStatus(fd);
        List<String> errorMessages = new ArrayList<String>();
        for (FileInfo file : files) {
          TachyonURI newPath = new TachyonURI(file.getPath());
          try {
            persist(newPath);
          } catch (IOException e) {
            errorMessages.add(e.getMessage());
          }
        }
        if (errorMessages.size() != 0) {
          throw new IOException(Joiner.on('\n').join(errorMessages));
        }
      } else if (fInfo.isIsPersisted()) {
        System.out.println(filePath + " is already persisted");
      } else {
        long size = TachyonFileSystemUtils.persistFile(mTfs, fd, fInfo, mTachyonConf);
        System.out.println("persisted file " + filePath + " with size " + size);
      }
    } catch (TachyonException e) {
      throw new IOException(e.getMessage());
    }
  }