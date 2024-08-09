@Override
    public int hashCode() {
        return transition.hashCode() ^ transitionAfter.getOffset().hashCode();
    }