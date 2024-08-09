@Override
  public int create(String path, @mode_t long mode, FuseFileInfo fi) {
    // mode is ignored in alluxio-fuse
    final AlluxioURI turi = mPathResolverCache.getUnchecked(path);
    // (see {@code man 2 open} for the structure of the flags bitfield)
    // File creation flags are the last two bits of flags
    final int flags = fi.flags.get();
    LOG.trace("create({}, {}) [Tachyon: {}]", path, Integer.toHexString(flags), turi);
    final int openFlag = flags & 3;
    if (openFlag != O_WRONLY.intValue()) {
      OpenFlags flag = OpenFlags.valueOf(openFlag);
      LOG.error("Passed a {} flag to create(). Files can only be created in O_WRONLY mode ({})",
          flag.toString(), path);
      return -ErrorCodes.EACCES();
    }

    try {
      synchronized (mOpenFiles) {
        if (mOpenFiles.size() >= MAX_OPEN_FILES) {
          LOG.error("Cannot open {}: too many open files (MAX_OPEN_FILES: {})",
              turi, MAX_OPEN_FILES);
          return -ErrorCodes.EMFILE();
        }

        final OpenFileEntry ofe = new OpenFileEntry(null, mFileSystem.createFile(turi));
        LOG.debug("Tachyon OutStream created for {}", path);
        mOpenFiles.put(mNextOpenFileId, ofe);
        fi.fh.set(mNextOpenFileId);

        // Assuming I will never wrap around (2^64 open files are quite a lot anyway)
        mNextOpenFileId += 1;
      }
      LOG.debug("{} created and opened in O_WRONLY mode", path);

    } catch (FileAlreadyExistsException e) {
      LOG.debug("File {} already exists", turi, e);
      return -ErrorCodes.EEXIST();
    } catch (IOException e) {
      LOG.error("IOException on {}", path, e);
      return -ErrorCodes.EIO();
    } catch (AlluxioException e) {
      LOG.error("TachyonException on {}", path, e);
      return -ErrorCodes.EFAULT();
    } catch (Throwable e) {
      LOG.error("Unexpected exception on {}", path, e);
      return -ErrorCodes.EFAULT();
    }

    return 0;
  }