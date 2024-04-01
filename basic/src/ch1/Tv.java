package ch1;

public interface Tv {
    // 인터페이스
    // 완전 추상화
    // 추상 메소드 - pulic(Default)
    // 객체 생성 불가
    void powerOn();

    void powerOff();

    void volumeUp();

    void volumeDown();

    void setSpeaker(speaker speaker);
}
