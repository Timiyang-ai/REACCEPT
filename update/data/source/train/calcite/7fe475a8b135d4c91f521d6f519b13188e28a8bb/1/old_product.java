public static List<Integer> asList(final int[] args) {
    return new AbstractList<Integer>() {
      public Integer get(int index) {
        return args[index];
      }

      public int size() {
        return args.length;
      }

      public Integer set(int index, Integer element) {
        return args[index] = element;
      }
    };
  }