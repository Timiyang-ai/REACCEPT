@Override public boolean checkTimeout(long timeInFut) {
        return remainingTime(U.currentTimeMillis() + timeInFut) <= 0;
    }