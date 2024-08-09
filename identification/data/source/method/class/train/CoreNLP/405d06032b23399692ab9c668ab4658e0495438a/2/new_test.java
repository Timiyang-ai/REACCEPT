  private String printTree(Tree tree, String mode) {
    TreePrint treePrint = new TreePrint(mode);
    StringWriter writer = new StringWriter();
    PrintWriter wrapped = new PrintWriter(writer);
    treePrint.printTree(tree, wrapped);
    wrapped.close();
    String out = writer.toString();
    return out;
  }