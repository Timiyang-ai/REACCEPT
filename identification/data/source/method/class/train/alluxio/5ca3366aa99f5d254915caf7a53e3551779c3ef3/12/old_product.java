@Override
  public int create(String path, @mode_t long mode, FuseFileInfo fi) {
    final AlluxioURI uri = mPathResolverCache.getUnchecked(path);
    final int flags = fi.flags.get();
    LOG.trace("create({}, {}) [Alluxio: {}]", path, Integer.toHexString(flags), uri);

    try {
      synchronized (mOpenFiles) {
        if (mOpenFiles.size() >= MAX_OPEN_FILES) {
          LOG.error("Cannot open {}: too many open files (MAX_OPEN_FILES: {})", uri,
              MAX_OPEN_FILES);
          return -ErrorCodes.EMFILE();
        }

        final OpenFileEntry ofe = new OpenFileEntry(null, mFileSystem.createFile(uri));
        LOG.debug("Alluxio OutStream created for {}", path);
        mOpenFiles.put(mNextOpenFileId, ofe);
        fi.fh.set(mNextOpenFileId);

        // Assuming I will never wrap around (2^64 open files are quite a lot anyway)
        mNextOpenFileId += 1;
      }
      LOG.debug("{} created and opened", path);
    } catch (FileAlreadyExistsException e) {
      LOG.debug("File {} already exists", uri, e);
      return -ErrorCodes.EEXIST();
    } catch (IOException e) {
      LOG.error("IOException on  {}", uri, e);
      return -ErrorCodes.EIO();
    } catch (AlluxioException e) {
      LOG.error("AlluxioException on {}", uri, e);
      return -ErrorCodes.EFAULT();
    } catch (Throwable e) {
      LOG.error("Unexpected exception on {}", path, e);
      return -ErrorCodes.EFAULT();
    }

    return 0;
  }