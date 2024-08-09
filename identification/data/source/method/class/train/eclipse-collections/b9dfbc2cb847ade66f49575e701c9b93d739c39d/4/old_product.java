public IntInterval toReversed()
    {
        return IntInterval.fromToBy(this.to, this.from, -this.step);
    }