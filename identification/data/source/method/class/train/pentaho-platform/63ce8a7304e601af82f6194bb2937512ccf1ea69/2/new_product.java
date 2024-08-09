public static Node addNode( Node parentNode, String nodeNamePrefix, String nodeName, String nodeParameter ) throws RepositoryException {
    return checkAddNode( parentNode, nodeNamePrefix + JcrStringHelper.fileNameEncode( nodeName ), nodeParameter );
  }