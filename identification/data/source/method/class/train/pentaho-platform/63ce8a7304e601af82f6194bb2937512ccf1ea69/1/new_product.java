public static Node addNode( Node parentNode, String nodeNamePrefix, String nodeName ) throws RepositoryException {
    return checkAddNode( parentNode, nodeNamePrefix + JcrStringHelper.fileNameEncode( nodeName ) );
  }