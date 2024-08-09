@Override
  public void copyOutputs(Path execRoot) throws IOException {
    for (PathFragment output : outputs) {
      Path source = sandboxExecRoot.getRelative(output);
      Path target = execRoot.getRelative(output);
      if (source.isFile() || source.isSymbolicLink()) {
        // Ensure the target directory exists in the real execroot. The directories for the action
        // outputs have already been created, but the spawn outputs may be different from the
        // overall action outputs. This is the case for test actions.
        FileSystemUtils.createDirectoryAndParents(target.getParentDirectory());
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