public void removeLinkDetails(OspfRouter ospfRouter, OspfLinkTed ospfLinkTed) {
        agent.deleteLink(ospfRouter, ospfLinkTed);
    }