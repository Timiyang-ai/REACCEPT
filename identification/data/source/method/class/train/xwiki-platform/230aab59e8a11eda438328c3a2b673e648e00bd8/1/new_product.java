@Override
    public URL findResource(final String name)
    {
        return AccessController.doPrivileged(new PrivilegedAction<URL>()
        {
            public URL run()
            {
                return URIClassLoader.this.finder.findResource(name);
            }
        }, this.acc);
    }