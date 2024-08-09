@Override
   public void setValue(Object value)
   {
      setText((value == null) ? "" : dateFormatter.format(value));
   }