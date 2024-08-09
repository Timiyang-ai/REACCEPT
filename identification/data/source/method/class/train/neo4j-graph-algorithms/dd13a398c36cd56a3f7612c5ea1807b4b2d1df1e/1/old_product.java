private boolean dijkstra(int source, int target, Direction direction, int maxDepth) {
        costs.clear();
        queue.clear();
        path.clear();
        visited.clear();
        costs.put(source, 0.0);
        queue.add(source, 0.0);
        Arrays.fill(depth, 1);
        while (!queue.isEmpty() && terminationFlag.running()) {
            int node = queue.pop();
            final int d = depth[node];
            if (d >= maxDepth) {
                continue;
            }
            if (node == target) {
                return true;
            }
            visited.set(node);
            double costs = this.costs.getOrDefault(node, Double.MAX_VALUE);
            graph.forEachRelationship(
                    node,
                    direction, (s, t, relId) -> {
                        if (!filter.accept(s, t, relId)) {
                            return true;
                        }
                        final double w = graph.weightOf(s, t);
                        final boolean updateCosts = updateCosts(s, t, w + costs);
                        if (!visited.get(t)) {
                            depth[t] = d + 1;
                            if (updateCosts) {
                                queue.update(t);
                            } else {
                                queue.add(t, w);
                            }
                        }
                        return terminationFlag.running();
                    });
        }
        return false;
    }