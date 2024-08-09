public String reflectionName() {
    return enclosingClassName != null
        ? (enclosingClassName.reflectionName() + '$' + simpleName)
        : (packageName.isEmpty() ? simpleName : packageName + '.' + simpleName);
  }