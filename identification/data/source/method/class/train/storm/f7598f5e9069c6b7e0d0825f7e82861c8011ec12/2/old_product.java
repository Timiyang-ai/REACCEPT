public void setUpContext(final Class<?> ownerCls, final ShellProcess process,
                             final TopologyContext context) {
        this.log = getLogger(ownerCls);
        this.process = process;
        // context is not used by the default implementation, but is included
        // in the interface in case it is useful to subclasses
    }