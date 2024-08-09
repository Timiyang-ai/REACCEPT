    @Test
    public void count()
    {
        this.iterables.forEach(Procedures.cast(this::basicCount));
    }