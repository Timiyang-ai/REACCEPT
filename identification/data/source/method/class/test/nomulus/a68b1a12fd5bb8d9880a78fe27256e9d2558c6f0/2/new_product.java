void transact(Runnable work) {
    transact(
        () -> {
          work.run();
          return null;
        });
  }