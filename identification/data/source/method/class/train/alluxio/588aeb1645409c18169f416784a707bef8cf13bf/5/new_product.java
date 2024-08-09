public static void createStorageDirPath(String path) throws IOException {
    if(Files.exists(Paths.get(path))){
      return;
    }
    Path storagePath;
    try {
      storagePath = Files.createDirectories(Paths.get(path));
    }catch (FileAlreadyExistsException e1){
      throw new IOException("Failed to create folder " + path);
    }catch (UnsupportedOperationException e2){
      throw new IOException("Failed to create folder " + path);
    }catch (SecurityException e3){
      throw new IOException("Failed to create folder " + path);
    }catch (IOException e4){
      throw new IOException("Failed to create folder " + path);
    }
    String absolutePath = storagePath.toAbsolutePath().toString();
    changeLocalFileToFullPermission(absolutePath);
    setLocalDirStickyBit(absolutePath);
    LOG.info("Folder {} was created!", path);
  }