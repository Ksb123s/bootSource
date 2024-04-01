package ch1;

public class Tvmain {
    public static void main(String[] args) {

        // LgTv LgTv = new LgTv();
        // SamsungTv samsungTv = new SamsungTv();

        Tv tv = new LgTv();
        tv.setSpeaker(new BritzSpeaker());

        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
    }
}
