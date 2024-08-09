public static String diff(String expression) throws InvalidExpressionException {

        ExpressionTree expTree = parseToTree(expression);
        expTree.derive();
        expTree.reduce();

        return expTree.toString();
    }