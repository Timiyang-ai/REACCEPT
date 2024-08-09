public static String diff(String expression) throws InvalidExpressionException {

        ExpressionTree expTree = parseToTree(expression);
expTree.printTree();
        expTree.derive();
expTree.printTree();
        expTree.reduce();

        return expTree.toString();
    }