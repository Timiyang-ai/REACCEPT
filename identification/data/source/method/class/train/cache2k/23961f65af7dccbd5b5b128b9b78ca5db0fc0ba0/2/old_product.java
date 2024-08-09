private void eventuallyThrowException(List<Throwable> _suppressedExceptions) {
    if (_suppressedExceptions.size() > 0) {
      Throwable _error = null;
      for (Throwable t : _suppressedExceptions) {
        if (t instanceof Error) { _error = t; }
        if (t instanceof ExecutionException &&
          ((ExecutionException) t).getCause() instanceof Error) {
          _error = t;
        }
      }
      Throwable _throwNow;
      String _text = "shutdown";
      if (_suppressedExceptions.size() > 1) {
        _text = " (" + _suppressedExceptions.size() + " exceptions)";
      }
      if (_error != null) {
        _throwNow = new CacheInternalError(_text, _error);
      } else {
        _throwNow = new CacheException(_text, _suppressedExceptions.get(0));
        _suppressedExceptions.remove(0);
      }
      for (Throwable t : _suppressedExceptions) {
        if (t != _error) {
          _throwNow.addSuppressed(t);
        }
      }
      if (_error != null) {
        throw (Error) _throwNow;
      } else {
        throw (RuntimeException) _throwNow;
      }
    }
  }