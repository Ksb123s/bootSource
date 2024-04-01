package ch1;

public class SamsungTv implements Tv {

    private speaker speaker;

    @Override
    public void powerOn() {
        System.out.println("SamsungTv - 전원on");
    }

    @Override
    public void powerOff() {
        System.out.println("SamsungTv - 전원off");
    }

    @Override
    public void volumeUp() {
        System.out.println("SamsungTv - volune up");
    }

    @Override
    public void volumeDown() {
        System.out.println("SamsungTv - volune down");
    }

    @Override
    public void setSpeaker(speaker speaker) {
        this.speaker = speaker;
    }

}
