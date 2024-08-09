public Boolean subscribe(final String callback, final String topic, final String verify, final long lease_seconds, final String secret,
            final String verify_token) {
        Logger.getLogger(Hub.class.getName()).log(Level.FINE, "{0} wants to subscribe to {1}", new Object[] { callback, topic });
        try {
            try {
                assert callback != null : "Callback URL is required.";
                assert topic != null : "Topic URL is required.";

                final URI uri = new URI(callback);
                assert validSchemes.contains(uri.getScheme()) : "Not a valid protocol " + uri.getScheme();
                assert validPorts.isEmpty() || validPorts.contains(uri.getPort()) : "Not a valid port " + uri.getPort();
                assert validTopics.isEmpty() || validTopics.contains(topic) : "Not a supported topic " + topic;
            } catch (final URISyntaxException ex) {
                assert false : "Not a valid URI " + callback;
            }
            assert verify != null && (verify.equals(Subscriber.VERIFY_ASYNC) || verify.equals(Subscriber.VERIFY_SYNC)) : "Unexpected verify value " + verify;

            final Subscriber subscriber = new Subscriber();
            subscriber.setCallback(callback);
            subscriber.setLeaseSeconds(lease_seconds);
            subscriber.setSecret(secret);
            subscriber.setTopic(topic);
            subscriber.setVerify(verify);
            subscriber.setVertifyToken(verify_token);

            final VerificationCallback verified = new VerificationCallback() {
                @Override
                public void onVerify(final boolean verified) {
                    if (verified) {
                        Logger.getLogger(Hub.class.getName()).log(Level.FINE, "Verified {0} subscribed to {1}",
                                new Object[] { subscriber.getCallback(), subscriber.getTopic() });
                        dao.addSubscriber(subscriber);
                    }
                }
            };

            if (Subscriber.VERIFY_SYNC.equals(subscriber.getVerify())) {
                final boolean result = verifier.verifySubcribeSyncronously(subscriber);
                verified.onVerify(result);

                return result;
            } else {
                verifier.verifySubscribeAsyncronously(subscriber, verified);

                return null;
            }
        } catch (final AssertionError ae) {
            throw new HttpStatusCodeException(400, ae.getMessage(), ae);
        } catch (final Exception e) {
            throw new HttpStatusCodeException(500, e.getMessage(), e);
        }
    }