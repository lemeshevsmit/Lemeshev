package com.shpp.p2p.cs.olemeshev.assignment4;

/**
 * Class create new thread and play audio
 *
 * @author Alexander Lemeshev
 */
public class MyAudioPlayer extends Thread {
    private String track;

    /**
     * constructor
     *
     * @param track path to file
     */
    public MyAudioPlayer(String track) {
        this.track = track;
    }

    /**
     * This method start new thread with method playAudio
     */
    @Override
    public void run() {
        playAudio(track);
    }

    /**
     * This method play audio. I use Prinston library
     *
     * @param track path to audio file
     */
    public void playAudio(String track) {
        double[] clip = StdAudio.read(track);
        StdAudio.play(clip);
    }
}

