@NonNull
    int[] buildDeepLinkIds() {
        ArrayDeque<NavDestination> hierarchy = new ArrayDeque<>();
        hierarchy.add(this);
        while (hierarchy.peekFirst().getParent() != null) {
            hierarchy.addFirst(hierarchy.peekFirst().getParent());
        }
        int[] deepLinkIds = new int[hierarchy.size()];
        int index = 0;
        for (NavDestination destination : hierarchy) {
            deepLinkIds[index++] = destination.getId();
        }
        return deepLinkIds;
    }