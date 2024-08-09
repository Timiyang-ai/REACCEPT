@Override
    public Driver merge(Driver other) {
        // Merge the behaviours.
        ImmutableMap.Builder<Class<? extends Behaviour>, Class<? extends Behaviour>>
                behaviours = ImmutableMap.builder();
        behaviours.putAll(this.behaviours);
        other.behaviours().forEach(b -> behaviours.put(b, other.implementation(b)));

        // Merge the properties.
        ImmutableMap.Builder<String, String> properties = ImmutableMap.builder();
        properties.putAll(this.properties).putAll(other.properties());

        return new DefaultDriver(name, manufacturer, hwVersion, swVersion,
                                 behaviours.build(), properties.build());
    }