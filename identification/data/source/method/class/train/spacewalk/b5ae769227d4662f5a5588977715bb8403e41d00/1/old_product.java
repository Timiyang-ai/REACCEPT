public static Channel subscribeToChildChannelByOSProduct(User user, Server current,
                String osProductName) {

        /*
         * First make sure that we have a base channel.
         * Second, make sure that the base channel has an RHN Tools child channel.
         * Third, try to subscribe to that child channel.
         */
        if (current.getBaseChannel() == null) {
            log.debug("base channel for server is null. returning null");
            return null;
        }

        Channel baseChannel = current.getBaseChannel();
        Channel foundChannel = null;

        Iterator i = ChannelManager.userAccessibleChildChannels(
                user.getOrg().getId(), baseChannel.getId()).iterator();
        while (i.hasNext()) {
            Channel child = (Channel) i.next();
            Set distChannelMaps = child.getDistChannelMaps();
            log.debug("distChannelMaps null? " + (distChannelMaps == null));
            if (distChannelMaps != null) {
                Iterator di = distChannelMaps.iterator();
                while (di.hasNext()) {
                    DistChannelMap dcm = (DistChannelMap) di.next();
                    log.debug("got DistChannelMap: " + dcm);
                    if (dcm.getOs().equals(osProductName)) {
                        log.debug("found a possible channel: " + dcm.getChannel());
                        foundChannel = dcm.getChannel();
                        if (SystemManager.canServerSubscribeToChannel(user.getOrg(),
                                current, dcm.getChannel())) {
                            log.debug("we can subscribe.  lets set foundChannel");
                            foundChannel = dcm.getChannel();
                            break;
                        }
                        else {
                            log.debug("no subscriptions available.");
                        }
                    }
                }
            }
        }

        if (foundChannel != null) {
           log.debug("we found a channel, now lets see if we should sub");
           if (!current.isSubscribed(foundChannel)) {
                if (log.isDebugEnabled()) {
                    log.debug("subChildChannelByOSProduct " +
                            "Subscribing server to channel: " + foundChannel);
                }
                SystemManager.subscribeServerToChannel(user, current, foundChannel);
           }
        }
        log.debug("subscribeToChildChannelByOSProduct returning: " + foundChannel);
        return foundChannel;

    }