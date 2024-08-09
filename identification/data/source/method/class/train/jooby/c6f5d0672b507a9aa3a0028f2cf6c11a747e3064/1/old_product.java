static @Nonnull List<String> pathKeys(@Nonnull String pattern) {
    List<String> result = new ArrayList<>();
    int start = -1;
    int end = Integer.MAX_VALUE;
    int len = pattern.length();
    for (int i = 0; i < len; i++) {
      char ch = pattern.charAt(i);
      switch (ch) {
        case '{': {
          start = i + 1;
          end = Integer.MAX_VALUE;
        }
        break;
        case ':': {
          end = i;
        }
        break;
        case '}': {
          String id = pattern.substring(start, Math.min(i, end));
          result.add(id);
          start = -1;
          end = Integer.MAX_VALUE;
        }
        break;
        case '*': {
          if (i == len - 1) {
            result.add("*");
          } else {
            result.add(pattern.substring(i + 1));
          }
          i = len;
        }
        break;
      }
    }
    switch (result.size()) {
      case 0:
        return Collections.emptyList();
      case 1:
        return Collections.singletonList(result.get(0));
      default:
        return unmodifiableList(result);
    }
  }