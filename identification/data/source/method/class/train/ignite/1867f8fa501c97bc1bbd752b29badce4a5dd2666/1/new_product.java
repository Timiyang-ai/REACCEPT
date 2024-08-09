@Override public boolean checkTimeout(long timeInFut) {
        return remainingTime(System.nanoTime() + U.millisToNanos(timeInFut)) <= 0;
    }