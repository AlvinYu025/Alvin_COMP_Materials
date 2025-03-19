public class VideoTester {
    public static void main(String[] args) {
        VideoTester p = new VideoTester();
        p.correctClassTest();
        System.out.println("------------------");
        p.videoTester();
        System.out.println("------------------");
        p.liveVideoTester();
        System.out.println("------------------");
        p.adVideoTester();
        
    }
    private void videoTester() {
        Video v = new Video("How to code with Java in 1 hour", 3600);
        System.out.println(v);
        v.skip(-10);
        System.out.println("Skip -10 seconds");
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);
        System.out.println("Skip 5 seconds");
        v.skip(5);
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);
        System.out.println("Skip -10 seconds");
        v.skip(-10);
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);
        System.out.println("Skip 20000 seconds");
        v.skip(20000);
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);


    }
    private void liveVideoTester() {
        LiveVideo v = new LiveVideo("Breaking News - COMP2045 exam paper leaked!");
        System.out.println(v);
        v.skip(-10);
        System.out.println("Skip -10 seconds");
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);
        System.out.println("Skip 5 seconds");
        v.skip(5);
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);
        System.out.println("Skip -10 seconds");
        v.skip(-10);
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);
        System.out.println("Skip 20000 seconds");
        v.skip(20000);
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);

        v.stop();
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);
    }

    private void adVideoTester() {
        AdVideo v = new AdVideo("Advertisment Clip", 3);
        System.out.println(v);
        v.skip(-10);
        System.out.println("Skip -10 seconds");
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);
        System.out.println("Skip 5 seconds");
        v.skip(5);
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);
        System.out.println("Skip -10 seconds");
        v.skip(-10);
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);
        System.out.println("Skip 20000 seconds");
        v.skip(20000);
        System.out.println(v);
        System.out.printf("Play one second, success? %b\n", v.play());
        System.out.println(v);



    }
    public void correctClassTest() {
        Video v1 = new Video("COMP2026", 100);
        Video v2 = new LiveVideo("COMP2026");
        Video v3 = new AdVideo("COMP2026", 100);

        if (v1 instanceof Video && !(v1 instanceof AdVideo) && !(v1 instanceof LiveVideo)) 
            System.out.println("OK");
        if (v2 instanceof Video && v2 instanceof LiveVideo && !(v2 instanceof AdVideo)) 
            System.out.println("OK");
        if (v3 instanceof Video && v3 instanceof AdVideo && !(v3 instanceof LiveVideo)) 
            System.out.println("OK");

    }
}
