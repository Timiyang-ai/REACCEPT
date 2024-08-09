public synchronized void remove(ActiveMQDestination key, Object value) {
        if (key.isComposite()) {
            ActiveMQDestination[] destinations = key.getCompositeDestinations();
            for (int i = 0; i < destinations.length; i++) {
                ActiveMQDestination childDestination = destinations[i];
                remove(childDestination, value);
            }
            return;
        }
        String[] paths = key.getDestinationPaths();
        rootNode.remove(paths, 0, value);

    }