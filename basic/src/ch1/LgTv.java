package ch1;

public class LgTv implements Tv {

    // 멤버 변수 초기화
    // 기본형: int, long,String ....
    // 1) 정수: 0
    // 2) 불린 : false
    // 3) 실수 : 0.0
    // 참조형 : null 초기화
    private speaker speaker;

    public LgTv(speaker speaker) {
        this.speaker = speaker;
    }

    public LgTv() {
    }

    @Override
    public void powerOn() {
        System.out.println("LgTv - 전원on");
    }

    @Override
    public void powerOff() {
        System.out.println("LgTv - 전원off");
    }

    @Override
    public void volumeUp() {
        // System.out.println("LgTv - volune up");
        speaker.volumeUp();
    }

    @Override
    public void volumeDown() {
        speaker.volumeDown();
    }

    @Override
    public void setSpeaker(speaker speaker) {
        this.speaker = speaker;
    }

}
