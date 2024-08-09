private void ls(AlluxioURI path, boolean recursive) throws AlluxioException, IOException {
    List<URIStatus> statuses = listStatusSortedByIncreasingCreationTime(path);
    for (URIStatus status : statuses) {
      System.out.format(
          formatLsString(SecurityUtils.isSecurityEnabled(mConfiguration), status.isFolder(),
              FormatUtils.formatMode((short) status.getMode(), status.isFolder()),
              status.getOwner(), status.getGroup(), status.getLength(), status.getCreationTimeMs(),
              100 == status.getInMemoryPercentage(), status.getPath()));
      if (recursive && status.isFolder()) {
        ls(new AlluxioURI(path.getScheme(), path.getAuthority(), status.getPath()), true);
      }
    }
  }