protected static boolean checkAllValidResources(IResource[] resources) {
    for (IResource resource : resources) {
      if (resource.getFile() == null || !resource.getFile().exists()) {
        return false;
      }
    }

    return true;
  }