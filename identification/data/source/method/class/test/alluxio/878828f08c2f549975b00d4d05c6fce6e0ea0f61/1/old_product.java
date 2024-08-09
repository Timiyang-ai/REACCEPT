public static long persistFile(FileSystem fs, AlluxioURI uri, URIStatus status,
      Configuration conf) throws IOException, FileDoesNotExistException, AlluxioException {
    // TODO(manugoyal) move this logic to the worker, as it deals with the under file system
    Closer closer = Closer.create();
    long ret;
    try {
      OpenFileOptions options = OpenFileOptions.defaults().setReadType(ReadType.NO_CACHE);
      FileInStream in = closer.register(fs.openFile(uri, options));
      AlluxioURI dstPath = new AlluxioURI(status.getUfsPath());
      UnderFileSystem ufs = UnderFileSystem.get(dstPath.toString(), conf);
      String parentPath = dstPath.getParent().toString();
      if (!ufs.exists(parentPath) && !ufs.mkdirs(parentPath, true)) {
        throw new IOException("Failed to create " + parentPath);
      }
      // TODO(chaomin): should also propagate ancestor dirs permission to UFS.
      URIStatus uriStatus = fs.getStatus(uri);
      PermissionStatus ps = new PermissionStatus(uriStatus.getUserName(), uriStatus.getGroupName(),
          (short) uriStatus.getPermission());
      OutputStream out = closer.register(ufs.create(dstPath.getPath(),
          UnderFileSystemCreateOptions.defaults().setPermissionStatus(ps)));
      ret = IOUtils.copyLarge(in, out);
    } catch (Exception e) {
      throw closer.rethrow(e);
    } finally {
      closer.close();
    }
    // Tell the master to mark the file as persisted
    fs.setAttribute(uri, SetAttributeOptions.defaults().setPersisted(true));
    return ret;
  }