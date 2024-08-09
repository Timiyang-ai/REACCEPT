public void range(double[] q, double radius, List<Neighbor<double[], E>> neighbors, double recall, int T) {
        if (radius <= 0.0) {
            throw new IllegalArgumentException("Invalid radius: " + radius);
        }

        if (recall > 1 || recall < 0) {
            throw new IllegalArgumentException("Invalid recall: " + recall);
        }

        double alpha = 1 - Math.pow(1 - recall, 1.0 / hash.size());

        for (int i = 0; i < hash.size(); i++) {
            IntArrayList buckets = model.get(i).getProbeSequence(q, alpha, T);

            for (int j = 0; j < buckets.size(); j++) {
                int bucket = buckets.get(j);
                ArrayList<HashEntry> bin = hash.get(i).table[bucket % H];
                if (bin != null) {
                    for (HashEntry e : bin) {
                        if (e.bucket == bucket) {
                            if (q == e.key && identicalExcluded) {
                                continue;
                            }

                            double distance = Math.distance(q, e.key);
                            if (distance <= radius) {
                                boolean existed = false;
                                for (Neighbor<double[], E> n : neighbors) {
                                    if (e.index == n.index) {
                                        existed = true;
                                        break;
                                    }
                                }

                                if (!existed) {
                                    neighbors.add(new Neighbor<>(e.key, e.data, e.index, distance));
                                }
                            }
                        }
                    }
                }
            }
        }
    }