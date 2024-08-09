public static String constructName(
      String prefix, String shardTemplate, String suffix, int shardNum, int numShards) {
    // Matcher API works with StringBuffer, rather than StringBuilder.
    StringBuffer sb = new StringBuffer();
    sb.append(prefix);

    Matcher m = SHARD_FORMAT_RE.matcher(shardTemplate);
    while (m.find()) {
      boolean isShardNum = (m.group(1).charAt(0) == 'S');

      char[] zeros = new char[m.end() - m.start()];
      Arrays.fill(zeros, '0');
      DecimalFormat df = new DecimalFormat(String.valueOf(zeros));
      String formatted = df.format(isShardNum ? shardNum : numShards);
      m.appendReplacement(sb, formatted);
    }
    m.appendTail(sb);

    sb.append(suffix);
    return sb.toString();
  }