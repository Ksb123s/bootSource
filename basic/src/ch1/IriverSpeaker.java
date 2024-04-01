package ch1;

public class IriverSpeaker implements speaker {

    IriverSpeaker() {
        System.out.println("IriverSpeaker 생성");
    }

    @Override
    public void volumeUp() {

        System.out.println("IriverSpeaker volume Up");
    }

    @Override
    public void volumeDown() {

        System.out.println("IriverSpeaker volume Down");
    }

}
