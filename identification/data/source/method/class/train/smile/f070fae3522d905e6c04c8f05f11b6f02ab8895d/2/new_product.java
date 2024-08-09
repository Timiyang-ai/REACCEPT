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
        
        double[] key = null;
        int index = -1;
        double nearest = Double.MAX_VALUE;

        int[] cand = candidates.toArray();
        Arrays.sort(cand);
        int prev = -1;
        for (int i : cand) {
            if (i == prev) {
                continue;
            } else {
                prev = i;
            }

            double[] x = keys.get(i);
            double distance = MathEx.distance(q, x);
            if (distance < nearest) {
                index = i;
                nearest = distance;
                key = x;
            }
        }

        return index == -1 ? null : new Neighbor<>(key, data.get(index), index, nearest);
    }