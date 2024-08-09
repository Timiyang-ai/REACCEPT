public List<TreeNode> split(TreeNode node) {
        List<TreeNode> children = node.toConditional(featureId, value);
        node.setImpurity(impurity);
        return children;
    }