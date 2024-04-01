package ch1;

public class BritzSpeaker implements speaker {

    BritzSpeaker() {
        System.out.println("BritzSpeaker 생성");
    }

    @Override
    public void volumeUp() {

        System.out.println("BritzSpeaker volume Up");
    }

    @Override
    public void volumeDown() {

        System.out.println("BritzSpeaker volume Down");
    }

}
