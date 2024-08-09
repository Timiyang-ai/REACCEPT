public E setParameters(Object... parameters) {
        this.parameters = PositionalParameters.create(parameters);
        return (E)this;
    }