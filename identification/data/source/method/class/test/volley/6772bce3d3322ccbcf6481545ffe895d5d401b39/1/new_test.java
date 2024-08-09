    @Test
    public void add_notifiesListener() throws Exception {
        RequestQueue.RequestEventListener listener = mock(RequestQueue.RequestEventListener.class);
        RequestQueue queue = new RequestQueue(new NoCache(), mMockNetwork, 0, mDelivery);
        queue.addRequestEventListener(listener);
        StringRequest req = mock(StringRequest.class);

        queue.add(req);

        verify(listener).onRequestEvent(req, RequestQueue.RequestEvent.REQUEST_QUEUED);
        verifyNoMoreInteractions(listener);
    }