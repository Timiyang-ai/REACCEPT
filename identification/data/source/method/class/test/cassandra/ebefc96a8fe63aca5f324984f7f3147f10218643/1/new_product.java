java.util.concurrent.Future onControlMessageComplete(Future<?> future, StreamMessage msg)
    {
        ChannelFuture channelFuture = (ChannelFuture)future;
        Throwable cause = future.cause();
        if (cause == null)
            return null;

        Channel channel = channelFuture.channel();
        logger.error("{} failed to send a stream message/data to peer {}: msg = {}",
                     createLogTag(session, channel), connectionId, msg, future.cause());

        // StreamSession will invoke close(), but we have to mark this sender as closed so the session doesn't try
        // to send any failure messages
        return session.onError(cause);
    }