public static Map<String, Object> buildModelForTicketEmail(Organization organization,
                                                               Event event,
                                                               TicketReservation ticketReservation,
                                                               String ticketURL,
                                                               Ticket ticket) {
        Map<String, Object> model = new HashMap<>();
        model.put("organization", organization);
        model.put("event", event);
        model.put("ticketReservation", ticketReservation);
        model.put("ticketUrl", ticketURL);
        model.put("ticket", ticket);
        return model;
    }