public boolean createNewFile() throws IOException {
        if (0 == path.length()) {
            throw new IOException("No such file or directory");
        }
        if (!isDirectory()) {  // true for paths like "dir/..", which can't be files.
          int result = newFileImpl(properPath(true));
          switch (result) {
              case 0:
                  return true;
              case 1:
                  return false;
          }
        }
        throw new IOException("Cannot create: " + path);
    }