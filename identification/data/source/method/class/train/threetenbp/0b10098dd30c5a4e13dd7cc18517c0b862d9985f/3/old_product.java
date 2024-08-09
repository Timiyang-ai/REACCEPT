@Override
    public int hashCode() {
        return dateTime.hashCode() ^ (transition != null ? transition.hashCode() : offset.hashCode());
    }