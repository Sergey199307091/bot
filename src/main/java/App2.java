import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class App2 extends TelegramLongPollingBot {
    SimpleChatBot simpleChatBot = new SimpleChatBot();
    Keyboard keyboard = new Keyboard();
    Music music = new Music();


    public static void main(String[] args) throws IOException {


       ApiContextInitializer.init();
        TelegramBotsApi bot = new TelegramBotsApi();

        try {
            bot.registerBot(new App2());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }



    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //    sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
keyboard.setButtons(sendMessage);
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    public void sendAudio(Message message,File file) {
        SendAnimation sendAnimation = new SendAnimation();
        sendAnimation.setChatId(message.getChatId().toString());
        File file1 = new File("D:\\Java\\Zveri\\Animation\\1.gif");
        sendAnimation.setAnimation(file1);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
sendMessage.setText("Загружается...");

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        sendPhoto.setPhoto(music.getPhoto());


        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(message.getChatId().toString());
        sendAudio.setAudio(file);
        try {
            Message message3 = execute(sendMessage);
            Message message4 = execute(sendAnimation);
            Message message2 = execute(sendAudio);
            Message message1 = execute(sendPhoto);
        //   String j = sendMessage.getText();
        //   System.out.println(j);
          //  SendMessage message9 = new SendMessage().setChatId(message.getChatId().toString()).setText(j);
        //   Message sentOutMessage=execute(message9);
            if (sendMessage.getText().equals("Загружается..."))
            {
                DeleteMessage deleteMessage = new DeleteMessage();
                DeleteMessage deleteMessage1 = new DeleteMessage();
                deleteMessage.setChatId(message3.getChatId());
               deleteMessage.setMessageId(message3.getMessageId());
               deleteMessage1.setChatId(message4.getChatId());
               deleteMessage1.setMessageId(message4.getMessageId());

                //пауза
                execute(deleteMessage);
                execute(deleteMessage1);
            }



        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void onUpdateReceived(Update update) {
        String h = null;
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (update.getMessage().getText().equals("Биография")) {
                try {
                    execute(keyboard.sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if(update.getMessage().getText().equals("Помощь")){
                sendMsg(message,"Что бы прослушать одну из песен <<Зверей>> просто введи любую из команд:\n" +"\uD83D\uDCAC" +"  звери песни,  песни зверей,  включи зверей.\n Либо нажатием на кнопку Звери песни\n\n Или просто пишите мне сообщения на которые я обязательно отвечу " + "✏");
                return;
            }
        } else if (update.hasCallbackQuery()) {
            try {
                String g = update.getCallbackQuery().getData();
                if (g.equals("Закрыть")) {
                    DeleteMessage deleteMessage = new DeleteMessage();
                    deleteMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    deleteMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    execute(deleteMessage);
                }
               if(g.equals("Дальше")){
                   EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
                   InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                   InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                   InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
                   inlineKeyboardButton1.setText("Назад");

                   inlineKeyboardButton1.setCallbackData("Назад");
                   inlineKeyboardButton2.setText("Закрыть");

                   inlineKeyboardButton2.setCallbackData("Закрыть");
                   List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                   List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
                   keyboardButtonsRow1.add(inlineKeyboardButton1);
                   keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Дальше").setCallbackData("Дальше1"));

                   keyboardButtonsRow2.add(inlineKeyboardButton2);
                   List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                   rowList.add(keyboardButtonsRow1);
                   rowList.add(keyboardButtonsRow2);
                   inlineKeyboardMarkup.setKeyboard(rowList);
                   editMessageReplyMarkup.setChatId(update.getCallbackQuery().getMessage().getChatId());
                   editMessageReplyMarkup.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
EditMessageText editMessageText = new EditMessageText();
editMessageText.setChatId(update.getCallbackQuery().getMessage().getChatId());
editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
editMessageText.setReplyMarkup(inlineKeyboardMarkup);
editMessageText.setText("\uD83C\uDFA4" + "Состав:\nРоман Билык\n" +
        "Вячеслав Зарубов\n" +
        "Кирилл Афонин\n" +
        "Герман Осипов\n" +
        "Валентин Тарасов\n\n" +"\uD83C\uDFBC"+
        "Бывшие участники:\nКонстантин Лабецкий\n" +
        "Кирилл Антоненко\n" +
        "Владимир Хоружий\n" +
        "Андрей Гусев\n" +
        "Максим Леонов\n" +
        "Алексей Любчик\n" +
        "Михаил Краев");

                   editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);

                   execute(editMessageReplyMarkup);
                   execute(editMessageText);
                }
                if(g.equals("Дальше1")){
                    EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                    InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
                    inlineKeyboardButton1.setText("Назад");

                    inlineKeyboardButton1.setCallbackData("Назад1");
                    inlineKeyboardButton2.setText("Закрыть");

                    inlineKeyboardButton2.setCallbackData("Закрыть");
                    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                    List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
                   keyboardButtonsRow1.add(inlineKeyboardButton1);


                    keyboardButtonsRow2.add(inlineKeyboardButton2);
                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    rowList.add(keyboardButtonsRow1);
                    rowList.add(keyboardButtonsRow2);
                    inlineKeyboardMarkup.setKeyboard(rowList);
                    editMessageReplyMarkup.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    editMessageReplyMarkup.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    EditMessageText editMessageText = new EditMessageText();
                    editMessageText.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    editMessageText.setReplyMarkup(inlineKeyboardMarkup);
                    editMessageText.setText("Основатель и вокалист рок группы <<Звери>>\n " +"\uD83C\uDFA4"  +"Роман Витальевич Билык\n" +
                            "Родился 7 декабря 1977 года в Таганроге. В школе увлёкся гитарой, стал писать песни, исполнять их вместе с друзьями. Создал группу «Асимметрия». Окончил строительное ПТУ № 23 в Таганроге, а потом Донской межрегиональный колледж строительства и экономики.\n" +
                            "\n" +
                            "В 2000 году приехал в Москву, осенью 2000 года познакомился с композитором и режиссёром клипов Александром Войтинским \n"+
                            "В 2001 году они создали группу «Звери».\n" +
                            "\n" +
                            "В 2003 году у группы вышел дебютный альбом «Голод».\n" +
                            "\n" +
                            "26 марта 2004 года группа «Звери» собирает концертную площадку «Лужники».\n" +
                            "\n" +
                            "В 2005 году группа вошла в список самых богатых российских звёзд, согласно журналу «Forbes». На премии Муз-ТВ группа побеждала в номинации «Лучшая рок-группа» 9 раз."
                          );

                    editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);

                    execute(editMessageReplyMarkup);
                    execute(editMessageText);
                }
                if(g.equals("Назад")){
                    EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                  //  InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                    InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
                 //   inlineKeyboardButton1.setText("Назад");

                 //   inlineKeyboardButton1.setCallbackData("Назад");
                    inlineKeyboardButton2.setText("Закрыть");

                    inlineKeyboardButton2.setCallbackData("Закрыть");
                    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                    List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
                  //  keyboardButtonsRow1.add(inlineKeyboardButton1);
                    keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Дальше").setCallbackData("Дальше"));

                    keyboardButtonsRow2.add(inlineKeyboardButton2);
                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    rowList.add(keyboardButtonsRow1);
                    rowList.add(keyboardButtonsRow2);
                    inlineKeyboardMarkup.setKeyboard(rowList);
                    editMessageReplyMarkup.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    editMessageReplyMarkup.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    EditMessageText editMessageText = new EditMessageText();
                    editMessageText.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    editMessageText.setReplyMarkup(inlineKeyboardMarkup);
                    editMessageText.setText("«Звери» — российская поп-рок-группа, созданная Романом Билыком в 2001 году. Лауреат премии MTV Россия и премии «Дебют». На премии Муз-ТВ группа побеждала в номинации «Лучшая рок-группа» 9 раз.");

                    editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);

                    execute(editMessageReplyMarkup);
                    execute(editMessageText);
                }
                if(g.equals("Назад1")){
                    EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                      InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                    InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
                       inlineKeyboardButton1.setText("Назад");

                       inlineKeyboardButton1.setCallbackData("Назад");
                    inlineKeyboardButton2.setText("Закрыть");

                    inlineKeyboardButton2.setCallbackData("Закрыть");
                    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                    List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
                      keyboardButtonsRow1.add(inlineKeyboardButton1);
                    keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Дальше").setCallbackData("Дальше1"));

                    keyboardButtonsRow2.add(inlineKeyboardButton2);
                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    rowList.add(keyboardButtonsRow1);
                    rowList.add(keyboardButtonsRow2);
                    inlineKeyboardMarkup.setKeyboard(rowList);
                    editMessageReplyMarkup.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    editMessageReplyMarkup.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    EditMessageText editMessageText = new EditMessageText();
                    editMessageText.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    editMessageText.setReplyMarkup(inlineKeyboardMarkup);
                    editMessageText.setText("\uD83C\uDFA4" +"Состав:\nРоман Билык\n" +
                            "Вячеслав Зарубов\n" +
                            "Кирилл Афонин\n" +
                            "Герман Осипов\n" +
                            "Валентин Тарасов\n\n" +"\uD83C\uDFBC"+
                            "Бывшие участники:\n"+"Константин Лабецкий\n" +
                            "Кирилл Антоненко\n" +
                            "Владимир Хоружий\n" +
                            "Андрей Гусев\n" +
                            "Максим Леонов\n" +
                            "Алексей Любчик\n" +
                            "Михаил Краев");

                    editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);

                    execute(editMessageReplyMarkup);
                    execute(editMessageText);
                }

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
        if (message != null && message.hasText() && !message.getText().equals("Биография")) {
            boolean ai = true;

            try {

                h = simpleChatBot.sayInReturn(message.getText(), ai);

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (h.equals("music")) {

                sendAudio(message, music.getMusic());

                return;


            }
        }
        if (update.getMessage().getText().equals("Загружается...")) {

            //пауза несколько минут
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(update.getMessage().getChatId());
            deleteMessage.setMessageId(update.getMessage().getMessageId());
            try {
                Boolean message1 = execute(deleteMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

       if(update.getMessage().getText().equals("/start")){
String j = "Послушаем отличную музыку \uD83D\uDCC0  ";
          sendMsg(message,j);
           return;
       }
if(h!=null&& h.length()>2) {
   sendMsg(message, h);
}
    }



    @Override
    public String getBotUsername() {
        return "SergeyMy_bot";
    }

    @Override
    public String getBotToken() {
        return "1174193035:AAES2r0Aw0QLOGJ3R_D4V_kGTD7hSEil4cM";
        }


    }

