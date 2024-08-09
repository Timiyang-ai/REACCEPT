@Override
    public void setValue(Object value)
    {
        if (!(value instanceof Long))
            setText(EMPTY);
        else
        {
            long val = (Long) value;
            setText(dateFormatter.format(val - relativeStartTime));
        }
    }