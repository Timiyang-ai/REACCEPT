static void eventuallyThrowException(List<Throwable> _suppressedExceptions) {
    if (_suppressedExceptions.isEmpty()) {
      return;
    }
    Throwable _error = null;
    for (Throwable t : _suppressedExceptions) {
      if (t instanceof Error) { _error = t; break; }
      if (t instanceof ExecutionException &&
        t.getCause() instanceof Error) {
        _error = t.getCause();
        break;
      }
    }
    String _text = "Exception(s) during shutdown";
    if (_suppressedExceptions.size() > 1) {
      _text = " (" + (_suppressedExceptions.size() - 1)+ " more suppressed exceptions)";
    }
    if (_error != null) {
      throw new CacheInternalError(_text, _error);
    }
    throw new CacheException(_text, _suppressedExceptions.get(0));
  }