private void generate(int[] itemset, int size, Node node) {
        ItemSet set = new ItemSet(itemset, node.support);
        buffer.offer(set);

        if (node.children != null) {
            for (int i = 0; i < size; i++) {
                Node child = node.children[i];
                if (child != null && child.support >= minSupport) {
                    int[] newItemset = FPGrowth.insert(itemset, child.id);
                    generate(newItemset, i, child);
                }
            }
        }
    }