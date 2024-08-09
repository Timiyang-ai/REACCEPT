public void recognize(InputStream stream, RecognizeOptions options) {
      if (connected != true)
        connect(options);

      try {
        socket.sendMessage(RequestBody.create(WebSocket.TEXT, buildStartMessage(options).body().toString()));
        sendInputSteam(stream);
        socket.sendMessage(RequestBody.create(WebSocket.TEXT, buildStopMessage().body().toString()));
      } catch (IOException e) {
        e.printStackTrace();
      }


    }