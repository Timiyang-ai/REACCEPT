public List<Concept> getPathFromRoot() {
        LinkedList<Concept> path = new LinkedList<Concept>();

        Concept node = this;
        while (node != null) {
            path.addFirst(node);
            node = node.parent;
        }

        return path;
    }