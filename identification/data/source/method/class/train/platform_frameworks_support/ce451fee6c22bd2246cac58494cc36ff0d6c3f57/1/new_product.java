public void addDestination(@NonNull NavDestination node) {
        if (node.getId() == 0) {
            throw new IllegalArgumentException("Destinations must have an id."
                    + " Call setId() or include an android:id in your navigation XML.");
        }
        NavDestination existingDestination = mNodes.get(node.getId());
        if (existingDestination == node) {
            return;
        }
        if (node.getParent() != null) {
            throw new IllegalStateException("Destination already has a parent set."
                    + " Call NavGraph.remove() to remove the previous parent.");
        }
        if (existingDestination != null) {
            existingDestination.setParent(null);
        }
        node.setParent(this);
        mNodes.put(node.getId(), node);
    }