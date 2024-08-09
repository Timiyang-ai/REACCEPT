protected static List<AndroidElement> searchViews(AbstractNativeElementContext context, View root,
                                                    Predicate predicate, boolean findJustOne) {
    List<AndroidElement> elements = new ArrayList<AndroidElement>();
    if (root == null) {
      return elements;
    }
    ArrayDeque<View> queue = new ArrayDeque<View>();
    queue.add(root);
    while (!queue.isEmpty()) {
      View view = queue.pop();
      if (predicate.apply(view)) {
        elements.add(context.newAndroidElement(view));
        if (findJustOne) {
          break;
        }
      }
      if (view instanceof ViewGroup) {
        ViewGroup group = (ViewGroup) view;
        int childrenCount = group.getChildCount();
        for (int index = 0; index < childrenCount; index++) {
          queue.add(group.getChildAt(index));
        }
      }
    }
    return elements;
  }