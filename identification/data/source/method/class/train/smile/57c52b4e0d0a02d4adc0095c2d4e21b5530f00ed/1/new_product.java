public List<AssociationRule> learn(double confidence) {
        List<AssociationRule> list = new ArrayList<>();
        ttree = fim.buildTotalSupportTree();
        for (int i = 0; i < ttree.root.children.length; i++) {
            if (ttree.root.children[i] != null) {
                int[] itemset = {ttree.root.children[i].id};
                learn(null, list, itemset, i, ttree.root.children[i], confidence);
            }
        }
        return list;
    }