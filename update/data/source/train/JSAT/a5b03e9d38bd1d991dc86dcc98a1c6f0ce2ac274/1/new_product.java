public void applyFunction(Function1D f)
    {
        for(int i = 0; i < length(); i++)
            set(i, f.f(get(i)));
    }