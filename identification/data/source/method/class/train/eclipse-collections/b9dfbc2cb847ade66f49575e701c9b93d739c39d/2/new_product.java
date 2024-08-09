@Override
    public void each(IntProcedure procedure)
    {
        if (this.goForward())
        {
            for (int i = this.from; i <= this.to; i += this.step)
            {
                procedure.value(i);
            }
        }
        else
        {
            for (int i = this.from; i >= this.to; i += this.step)
            {
                procedure.value(i);
            }
        }
    }