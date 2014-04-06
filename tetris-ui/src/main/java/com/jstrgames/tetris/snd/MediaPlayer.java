package com.jstrgames.tetris.snd;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;

public class MediaPlayer {

	private final Sequencer sequencer;
	private final Receiver receiver;
	
	public MediaPlayer(String midiFile) 
			throws InvalidMidiDataException, IOException, MidiUnavailableException {
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		final InputStream stream = classLoader.getResourceAsStream(midiFile);
		final Sequence sequence = MidiSystem.getSequence(stream);

	    // Create a sequencer for the sequence
		this.sequencer = MidiSystem.getSequencer(false); 
		this.receiver = MidiSystem.getReceiver(); 

		sequencer.open(); 
		sequencer.getTransmitter().setReceiver(receiver); 
		sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		
		// play the MIDI 
		sequencer.setSequence(sequence); 

	}
	
	public void play() {
		sequencer.start();
	}

	public void setVolume(int volume) {
		ShortMessage volMessage = new ShortMessage(); 
		for (int i = 0; i < 16; i++) { 
			try { 
				volMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, volume); 
			} catch (InvalidMidiDataException e) {
				e.printStackTrace();
			} 
			receiver.send(volMessage, -1); 
		} 

	}
}
