private long getFrequentItemsets(PrintStream out, List<ItemSet> list, int[] itemset, int size, Node node) {
        ItemSet set = new ItemSet(itemset, node.support);
        if (out != null) {
            out.println(set);
        }

        if (list != null) {
            list.add(set);
        }

        long n = 1;
        if (node.children != null) {
            for (int i = 0; i < size; i++) {
                Node child = node.children[i];
                if (child != null && child.support >= minSupport) {
                    int[] newItemset = FPGrowth.insert(itemset, child.id);
                    n += getFrequentItemsets(out, list, newItemset, i, child);
                }
            }
        }

        return n;
    }