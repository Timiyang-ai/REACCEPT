    @Test
    public void onControlMessageComplete_HappyPath()
    {
        Assert.assertTrue(channel.isOpen());
        Assert.assertTrue(sender.connected());
        ChannelPromise promise = channel.newPromise();
        promise.setSuccess();
        Assert.assertNull(sender.onControlMessageComplete(promise, new CompleteMessage()));
        Assert.assertTrue(channel.isOpen());
        Assert.assertTrue(sender.connected());
        Assert.assertNotEquals(StreamSession.State.FAILED, session.state());
    }