@Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("DoubleMatrix.Builder{");
      buf.append("array").append('=').append(JodaBeanUtils.toString(array));
      buf.append('}');
      return buf.toString();
    }