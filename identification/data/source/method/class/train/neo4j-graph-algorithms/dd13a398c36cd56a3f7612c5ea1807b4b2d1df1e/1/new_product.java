private boolean dijkstra(int source, int target, Direction direction, int maxDepth) {
        costs.clear();
        queue.clear();
        path.clear();
        visited.clear();
        costs.put(source, 0.0);
        queue.add(source, 0.0);
        Arrays.fill(depth, 0);
        depth[source] = 1;
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
                        final UpdateResult updateCosts = updateCosts(s, t, w + costs);
                        if (!visited.get(t)) {
                            switch (updateCosts) {
                                case NO_PREVIOUS_COSTS:
                                    queue.add(t, w);
                                    break;
                                case UPDATED_COST:
                                    queue.update(t);
                                    break;
                                default:
                                    break;
                            }
                            depth[t] = depth[s] + 1;
                        }
                        return terminationFlag.running();
                    });
        }
        return false;
    }