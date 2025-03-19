//For convenient, I move the class Video here.
public class Video {
    private final String name;
    private final int length;
    protected int currentTime;
    public Video(String name, int length) {
        this.name = name;
        this.length = length;
        currentTime = 0;
    }
    public String getName() { return name; }
    public int getLength() { return length; }
    public void skip(int seconds) {
        currentTime = Math.max(Math.min(currentTime + seconds, length),0);
    }
    public String toString() {
        return name + " (" + currentTime + "/" + length + ")";
    }
    public boolean play() {
        if (currentTime == length) {
            System.out.println("Video finished");
            return false;
        }
        currentTime++;
        return true;
    }
}


/**
 * We did not model the start time of the video. Some students did. Well done!
 */
class LiveVideo extends Video {
    private boolean isEnd;
    public LiveVideo(String name) {
        super(name, 0);
        isEnd = false;
    }
    public String toString() {
        if (isEnd)
            return getName() + " (finished)";
        return getName() + " ([live] " + -currentTime +  " seconds ago)";
    }
    public void skip(int seconds) {
        if (currentTime + seconds > 0) {
            System.out.println("Cannot skip beyond the current time.");
        } else {
            currentTime = Math.min(currentTime + seconds, 0);
        }
    }
    // From the main you see the stop method. It stops the video.
    // When a video is stop, it should not be played. It should printed as "finished" in toString.
    public void stop() {
        isEnd = true;
    }
    public boolean play() {
        if (isEnd) {
            System.out.println("Video finished");
        } 
        return !isEnd;
    }
}

class AdVideo extends Video {
    public AdVideo(String name, int length) {
        super(name, length);
    }
    public void skip(int seconds) {
        System.out.println("Ad Video can't be skipped");
        return;
    }
    public String toString() {
        return super.toString() + " (ad)";
    }
}