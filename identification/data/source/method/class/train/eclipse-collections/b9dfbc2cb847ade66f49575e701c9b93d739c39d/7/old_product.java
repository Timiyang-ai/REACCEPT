public boolean contains(int value)
    {
        return this.isWithinBoundaries(value) && (value - this.from) % this.step == 0;
    }