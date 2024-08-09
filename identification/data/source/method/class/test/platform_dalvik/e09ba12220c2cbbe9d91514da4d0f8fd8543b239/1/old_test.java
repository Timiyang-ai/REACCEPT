@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "receive",
        args = {int.class}
    )
    public void test_receive() throws IOException {
        pis = new PipedInputStream();
        pos = new PipedOutputStream();

        // test if writer recognizes dead reader
        pis.connect(pos);
        class WriteRunnable implements Runnable {

            boolean pass = false;

            boolean readerAlive = true;

            public void run() {
                try {
                    pos.write(1);
                    while (readerAlive) {
                        Thread.sleep(100);
                    }
                    try {
                        // should throw exception since reader thread
                        // is now dead
                        pos.write(1);
                    } catch (IOException e) {
                        pass = true;
                    }
                } catch (IOException e) {
                    // ignore
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        }
        WriteRunnable writeRunnable = new WriteRunnable();
        Thread writeThread = new Thread(writeRunnable);
        class ReadRunnable implements Runnable {

            boolean pass;

            public void run() {
                try {
                    pis.read();
                    pass = true;
                } catch (IOException e) {}
            }
        }
        ;
        ReadRunnable readRunnable = new ReadRunnable();
        Thread readThread = new Thread(readRunnable);
        writeThread.start();
        readThread.start();
        while (readThread.isAlive())
            ;
        writeRunnable.readerAlive = false;
        assertTrue("reader thread failed to read", readRunnable.pass);
        while (writeThread.isAlive())
            ;
        assertTrue("writer thread failed to recognize dead reader",
                writeRunnable.pass);

        // attempt to write to stream after writer closed
        pis = new PipedInputStream();
        pos = new PipedOutputStream();

        pis.connect(pos);
        class MyRunnable implements Runnable {

            boolean pass;

            public void run() {
                try {
                    pos.write(1);
                } catch (IOException e) {
                    pass = true;
                }
            }
        }
        MyRunnable myRun = new MyRunnable();
        synchronized (pis) {
            t = new Thread(myRun);
            // thread t will be blocked inside pos.write(1)
            // when it tries to call the synchronized method pis.receive
            // because we hold the monitor for object pis
            t.start();
            try {
                // wait for thread t to get to the call to pis.receive
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            // now we close
            pos.close();
        }
        // we have exited the synchronized block, so now thread t will make
        // a call to pis.receive AFTER the output stream was closed,
        // in which case an IOException should be thrown
        while (t.isAlive()) {
            ;
        }
        assertTrue(
                "write failed to throw IOException on closed PipedOutputStream",
                myRun.pass);
    }