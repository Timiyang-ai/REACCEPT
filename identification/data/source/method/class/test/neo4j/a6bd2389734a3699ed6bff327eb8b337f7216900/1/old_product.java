@Override
    public T evaluate()
    {
        T result;
        if ( (result = reference) == null )
        {
            synchronized ( this )
            {
                if ( (result = reference) == null )
                {
                    result = reference = create();
                }
            }
        }
        return result;
    }