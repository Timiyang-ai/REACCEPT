@Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("DoubleMatrix.Builder{");
      buf.append("array").append('=').append(JodaBeanUtils.toString(array)).append(',').append(' ');
      buf.append("rows").append('=').append(JodaBeanUtils.toString(rows)).append(',').append(' ');
      buf.append("columns").append('=').append(JodaBeanUtils.toString(columns)).append(',').append(' ');
      buf.append("elements").append('=').append(JodaBeanUtils.toString(elements));
      buf.append('}');
      return buf.toString();
    }