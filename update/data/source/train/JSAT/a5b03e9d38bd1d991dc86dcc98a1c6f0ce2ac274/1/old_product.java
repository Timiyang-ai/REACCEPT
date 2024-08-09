public void applyFunction(Function f)
    {
        for(int i = 0; i < length(); i++)
            set(i, f.f(get(i)));
    }