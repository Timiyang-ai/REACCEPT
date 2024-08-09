public E setParameters(Object... parameters) {
        this.parameters = ArrayParameters.create(parameters);
        return (E)this;
    }