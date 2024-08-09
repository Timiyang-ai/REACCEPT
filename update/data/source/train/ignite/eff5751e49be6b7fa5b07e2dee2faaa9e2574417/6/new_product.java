public List<TreeNode> split(TreeNode node) {
        List<TreeNode> children = node.toConditional(featureId, val);
        node.setImpurity(impurity);
        return children;
    }