private long learn(PrintStream out, List<AssociationRule> list, int[] itemset, int support, double confidence) {
        long n = 0;
        // Determine combinations
        int[][] combinations = getPowerSet(itemset);

        // Loop through combinations
        for (int i = 0; i < combinations.length; i++) {
            // Find complement of combination in given itemSet
            int[] complement = getComplement(combinations[i], itemset);
            // If complement is not empty generate rule
            if (complement != null) {
                double arc = getConfidence(combinations[i], support);
                if (arc >= confidence) {
                    double supp = (double) support / fim.size();
                    AssociationRule ar = new AssociationRule(combinations[i], complement, supp, arc);
                    n++;

                    if (out != null) {
                        out.println(ar);
                    }

                    if (list != null) {
                        list.add(ar);
                    }
                }
            }
        }
        
        return n;
    }