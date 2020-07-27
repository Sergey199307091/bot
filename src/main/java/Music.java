import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.Random;

public class Music {
    public File getMusic() {
        String[] music = new String[9];
        music[0] = "D:\\Java\\Zveri\\1.mp3";
        music[1] = "D:\\Java\\Zveri\\2.mp3";
        music[2] = "D:\\Java\\Zveri\\3.mp3";
        music[3] = "D:\\Java\\Zveri\\4.mp3";
        music[4] = "D:\\Java\\Zveri\\5.mp3";
        music[5] = "D:\\Java\\Zveri\\6.mp3";
        music[6] = "D:\\Java\\Zveri\\7.mp3";
        music[7] = "D:\\Java\\Zveri\\8.mp3";
        music[8] = "D:\\Java\\Zveri\\9.mp3";

        int l =(int)  (Math.random()*music.length);
        String music1 = music[l];
        File file = new File(music1);
        return file;
    }
    public File getPhoto(){
File file = new File("D:\\Java\\Zveri\\Photo\\1.png");
return file;
    }


}
