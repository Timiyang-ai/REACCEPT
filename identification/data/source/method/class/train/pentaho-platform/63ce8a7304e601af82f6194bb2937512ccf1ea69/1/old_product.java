public static Node addNode( Node parentNode, String nodeName, String nodeParameter ) throws RepositoryException {
    return checkAddNode( parentNode, JcrStringHelper.fileNameEncode( nodeName ), nodeParameter );
  }