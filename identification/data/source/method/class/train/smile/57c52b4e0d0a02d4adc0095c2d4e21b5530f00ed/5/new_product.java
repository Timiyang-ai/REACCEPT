public List<Concept> getPathToRoot() {
        LinkedList<Concept> path = new LinkedList<>();

        Concept node = this;
        while (node != null) {
            path.add(node);
            node = node.parent;
        }

        return path;
    }