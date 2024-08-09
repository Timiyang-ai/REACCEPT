public static Node addNode( Node parentNode, String nodeName ) throws RepositoryException {
    return checkAddNode( parentNode, JcrStringHelper.fileNameEncode( nodeName ) );
  }