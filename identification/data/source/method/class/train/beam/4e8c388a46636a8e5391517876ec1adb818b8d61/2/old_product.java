static String constructName(
      String prefix, String shardTemplate, String suffix, int shardNum, int numShards,
      String paneStr, String windowStr) {
    // Matcher API works with StringBuffer, rather than StringBuilder.
    StringBuffer sb = new StringBuffer();
    sb.append(prefix);

    Matcher m = SHARD_FORMAT_RE.matcher(shardTemplate);
    while (m.find()) {
      boolean isCurrentShardNum = (m.group(1).charAt(0) == 'S');
      boolean isNumberOfShards = (m.group(1).charAt(0) == 'N');
      boolean isPane = (m.group(1).charAt(0) == 'P') && paneStr != null;
      boolean isWindow = (m.group(1).charAt(0) == 'W') && windowStr != null;

      char[] zeros = new char[m.end() - m.start()];
      Arrays.fill(zeros, '0');
      DecimalFormat df = new DecimalFormat(String.valueOf(zeros));
      if (isCurrentShardNum) {
        String formatted = df.format(shardNum);
        m.appendReplacement(sb, formatted);
      } else if (isNumberOfShards) {
        String formatted = df.format(numShards);
        m.appendReplacement(sb, formatted);
      } else if (isPane) {
        m.appendReplacement(sb, paneStr);
      } else if (isWindow) {
        m.appendReplacement(sb, windowStr);
      }
    }
    m.appendTail(sb);

    sb.append(suffix);
    return sb.toString();
  }