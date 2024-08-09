public Neighbor<double[], E> nearest(double[] q, double recall, int T) {
        if (recall > 1 || recall < 0) {
            throw new IllegalArgumentException("Invalid recall: " + recall);
        }

        double alpha = 1 - Math.pow(1 - recall, 1.0 / hash.size());

        IntArrayList candidates = new IntArrayList();
        for (int i = 0; i < hash.size(); i++) {
            IntArrayList buckets = model.get(i).getProbeSequence(q, alpha, T);

            for (int j = 0; j < buckets.size(); j++) {
                int bucket = buckets.get(j);
                ArrayList<HashEntry> bin = hash.get(i).table[bucket % H];
                if (bin != null) {
                    for (HashEntry e : bin) {
                        if (e.bucket == bucket) {
                            if (q != e.key || !identicalExcluded) {
                                candidates.add(e.index);
                            }
                        }
                    }
                }
            }
        }
        
        Neighbor<double[], E> neighbor = new Neighbor<>(null, null, -1, Double.MAX_VALUE);

        int[] cand = candidates.toArray();
        Arrays.sort(cand);
        int prev = -1;
        for (int index : cand) {
            if (index == prev) {
                continue;
            } else {
                prev = index;
            }

            double[] key = keys.get(index);
            double distance = MathEx.distance(q, key);
            if (distance < neighbor.distance) {
                neighbor.index = index;
                neighbor.distance = distance;
                neighbor.key = key;
                neighbor.value = data.get(index);
            }
        }

        return neighbor;
    }