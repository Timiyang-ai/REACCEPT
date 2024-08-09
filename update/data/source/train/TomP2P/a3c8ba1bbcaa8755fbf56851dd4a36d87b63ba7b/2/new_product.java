public FutureDone<Pair<FutureRouting,FutureRouting>> bootstrap(final Collection<PeerAddress> peerAddresses,
            final RoutingBuilder routingBuilder, final ChannelCreator cc) {
        // search close peers
        LOG.debug("broadcast to {}", peerAddresses);
        final FutureDone<Pair<FutureRouting,FutureRouting>> futureDone = new FutureDone<Pair<FutureRouting,FutureRouting>>();

        // first we find close peers to us
        routingBuilder.bootstrap(true);

        final FutureRouting futureRouting0 = routing(peerAddresses, routingBuilder, Type.REQUEST_1, cc);
        // to not become a Fachidiot (expert idiot), we need to know other peers
        // as well. This is important if this peer is passive and only replies on requests from other peers
        futureRouting0.addListener(new BaseFutureAdapter<FutureRouting>() {
            @Override
            public void operationComplete(final FutureRouting future) throws Exception {
                // setting this to null causes to search for a random number
            	if(future.isSuccess()) {
            		routingBuilder.locationKey(null);
            		final FutureRouting futureRouting1 = routing(peerAddresses, routingBuilder, Type.REQUEST_1, cc);
            		futureRouting1.addListener(new BaseFutureAdapter<FutureRouting>() {
            			@Override
            			public void operationComplete(FutureRouting future) throws Exception {
            				final Pair<FutureRouting,FutureRouting> pair = new Pair<FutureRouting, FutureRouting>(futureRouting0, futureRouting1);
            				futureDone.done(pair);
            			}
            		});
            	} else {
            		futureDone.failed(future);
            	}
            }
        });
        return futureDone;
    }