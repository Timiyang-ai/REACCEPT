public void process(SimpleImageSequence<T> sequence) {

		// Figure out how large the GUI window should be
		T frame = sequence.next();
		gui.setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
		ShowImages.showWindow(gui,"KTL Tracker");

		// process each frame in the image sequence
		while( sequence.hasNext() ) {
			frame = sequence.next();

			// tell the tracker to process the frame
			tracker.process(frame);

			// if there are too few tracks spawn more
			if( tracker.getActiveTracks(null).size() < 100 )
				tracker.spawnTracks();

			// visualize tracking results
			updateGUI(sequence);

			// wait for a fraction of a second so it doesn't process to fast
			BoofMiscOps.pause(100);
		}
	}