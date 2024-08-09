@Override
  public boolean delete(Path cPath, boolean recursive) throws IOException {
    LOG.info("delete({}, {})", cPath, recursive);
    if (mStatistics != null) {
      mStatistics.incrementWriteOps(1);
    }
    TachyonURI path = new TachyonURI(Utils.getPathWithoutScheme(cPath));
    DeleteOptions options = new DeleteOptions.Builder().setRecursive(recursive).build();
    try {
      mTFS.delete(path, options);
      return true;
    } catch (InvalidPathException e) {
      LOG.info("delete failed: {}", e.getMessage());
      return false;
    } catch (FileDoesNotExistException e) {
      LOG.info("delete failed: {}", e.getMessage());
      return false;
    } catch (TachyonException e) {
      throw new IOException(e);
    }
  }