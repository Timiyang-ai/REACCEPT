public static Map<String, Object> buildModelForTicketPDF(Organization organization, Event event, TicketReservation ticketReservation, TicketCategory ticketCategory, Ticket ticket, Optional<ImageData> imageData) {
        String qrCodeText = ticket.ticketCode(event.getPrivateKey());
        //
        Map<String, Object> model = new HashMap<>();
        model.put("ticket", ticket);
        model.put("reservation", ticketReservation);
        model.put("ticketCategory", ticketCategory);
        model.put("event", event);
        model.put("organization", organization);

        model.put("qrCodeDataUri", "data:image/png;base64," + Base64.getEncoder().encodeToString(createQRCode(qrCodeText)));

        imageData.ifPresent(iData -> {
            model.put("eventImage", iData.getEventImage());
            model.put("imageWidth", iData.getImageWidth());
            model.put("imageHeight", iData.getEventImage());
        });

        model.put("deskPaymentRequired", Optional.ofNullable(ticketReservation.getPaymentMethod()).orElse(PaymentProxy.STRIPE).isDeskPaymentRequired());
        return model;
    }