@Override
  public void copyOutputs(Path execRoot) throws IOException {
    for (PathFragment output : outputs) {
      Path source = sandboxExecRoot.getRelative(output);
      Path target = execRoot.getRelative(output);
      if (source.isFile() || source.isSymbolicLink()) {
        Files.move(source.getPathFile(), target.getPathFile());
      } else if (source.isDirectory()) {
        try {
          source.renameTo(target);
        } catch (IOException e) {
          // Failed to move directory directly, thus move it recursively.
          target.createDirectory();
          FileSystemUtils.moveTreesBelow(source, target);
        }
      }
    }
  }