public URL findResource(final String name)
    {
        return (URL) AccessController.doPrivileged(new PrivilegedAction()
        {
            public Object run()
            {
                return finder.findResource(name);
            }
        }, acc);
    }