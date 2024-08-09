    protected void remove(String name, Object value) {
        ActiveMQDestination destination = createDestination(name);
        map.remove(destination, value);
    }