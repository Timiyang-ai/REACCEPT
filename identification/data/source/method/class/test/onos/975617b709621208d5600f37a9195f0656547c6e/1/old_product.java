DefaultDriver merge(DefaultDriver other) {
        // Merge the behaviours.
        ImmutableMap.Builder<Class<? extends Behaviour>, Class<? extends Behaviour>>
                behaviours = ImmutableMap.builder();
        behaviours.putAll(other.behaviours).putAll(this.behaviours);

        // Merge the properties.
        ImmutableMap.Builder<String, String> properties = ImmutableMap.builder();
        properties.putAll(other.properties).putAll(this.properties);

        return new DefaultDriver(name, manufacturer, hwVersion, swVersion,
                                 behaviours.build(), properties.build());
    }