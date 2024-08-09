@Override
    public String toString() {
      StringBuilder buf = new StringBuilder(224);
      buf.append("OvernightIndex.Builder{");
      buf.append("currency").append('=').append(JodaBeanUtils.toString(currency)).append(',').append(' ');
      buf.append("name").append('=').append(JodaBeanUtils.toString(name)).append(',').append(' ');
      buf.append("calendar").append('=').append(JodaBeanUtils.toString(calendar)).append(',').append(' ');
      buf.append("publicationDateOffset").append('=').append(JodaBeanUtils.toString(publicationDateOffset)).append(',').append(' ');
      buf.append("effectiveDateOffset").append('=').append(JodaBeanUtils.toString(effectiveDateOffset)).append(',').append(' ');
      buf.append("dayCount").append('=').append(JodaBeanUtils.toString(dayCount));
      buf.append('}');
      return buf.toString();
    }