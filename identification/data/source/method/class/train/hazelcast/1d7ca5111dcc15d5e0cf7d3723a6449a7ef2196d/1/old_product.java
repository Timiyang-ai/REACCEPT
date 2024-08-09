@Nonnull
    public List<Event> pollMCEvents() {
        List<Event> polled = new ArrayList<>();
        mcEvents.drainTo(polled);
        return polled;
    }