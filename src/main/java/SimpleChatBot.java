import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

class SimpleChatBot {

    final String[] COMMON_PHRASES = {
            "Нет ничего ценнее слов, сказанных к месту и ко времени.",
            "Порой молчание может сказать больше, нежели уйма слов.",
            "Перед тем как писать/говорить всегда лучше подумать.",
            "Вежливая и грамотная речь говорит о величии души.",
            "Приятно когда текст без орфографических ошибок.",
            "Многословие есть признак неупорядоченного ума.",
            "Слова могут ранить, но могут и исцелять.",
            "Записывая слова, мы удваиваем их силу.",
            "Кто ясно мыслит, тот ясно излагает.",
            "Боюсь Вы что-то не договариваете."};
    final String[] ELUSIVE_ANSWERS = {
            "Вопрос непростой, прошу тайм-аут на раздумья.",
            "Не уверен, что располагаю такой информацией.",
            "Может лучше поговорим о чём-то другом?",
            "Простите, но это очень личный вопрос.",
            "Не уверен, что Вам понравится ответ.",
            "Поверьте, я сам хотел бы это знать.",
            "Вы действительно хотите это знать?",
            "Уверен, Вы уже догадались сами.",
            "Зачем Вам такая информация?",
            "Давайте сохраним интригу?"};
    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {{
        // hello
        put("хай", "hello");
        put("привет", "hello");
        put("здорово", "hello");
        put("здравствуй", "hello");
        // who
        put("кто\\s.*ты", "who");
        put("ты\\s.*кто", "who");
        // name
        put("как\\s.*зовут", "name");
        put("как\\s.*имя", "name");
        put("есть\\s.*имя", "name");
        put("какое\\s.*имя", "name");
        put("как\\s.*тебя\\s.*зовут", "name");
        put("как\\s.*обращаться","name");
        put("твое\\s.*имя","name");
        // howareyou
        put("как\\s.*дела", "howareyou");
        put("как\\s.*жизнь", "howareyou");
        // whatdoyoudoing
        put("зачем\\s.*тут", "whatdoyoudoing");
        put("зачем\\s.*здесь", "whatdoyoudoing");
        put("что\\s.*делаешь", "whatdoyoudoing");
        put("чем\\s.*занимаешься", "whatdoyoudoing");
        // whatdoyoulike
        put("что\\s.*нравится", "whatdoyoulike");
        put("что\\s.*любишь", "whatdoyoulike");
        // iamfeelling
        put("кажется", "iamfeelling");
        put("чувствую", "iamfeelling");
        put("испытываю", "iamfeelling");
        // yes
        put("^да", "yes");
        put("согласен", "yes");
        // whattime
        put("который\\s.*час", "whattime");
        put("сколько\\s.*время", "whattime");
        // bye
        put("прощай", "bye");
        put("увидимся", "bye");
        put("до\\s.*свидания", "bye");
        put("какая\\s.*погода", "weather");
        //music
       put("звери\\s.*песни","music");
        put("песни\\s.*зверей","music");
        put("включи\\s.*зверей","music");
    }};

    Pattern pattern;
    Random random;
    Date date ;

    SimpleChatBot() {
        date = new Date();
        random = new Random();
    }






    public String getPatterns(String h) throws Exception {                 //получаю значение ключа и выбираю ответ
        SimpleChatBot simpleChatBot1 = new SimpleChatBot();
        boolean kl = false;

        switch(h){
            case "hello":
                String[]  hello = {"Здравствуйте, рад Вас видеть.","Привет","Хай","Здравствуйте","Привет,как дела?","Здаров"};
                int i =(int)  (Math.random()*hello.length);
                String   hello1 = hello[i];

                return hello1;
            case "who":
                String []nam = {"Меня зовут Чат Бот", "мое имя Чат Бот", "просто Чат Бот"};
                int lk =(int)  (Math.random()*nam.length);
                String   nam1 = nam[lk];
                return nam1;
            case "name":
                String []name = {"Меня зовут Чат Бот", "мое имя Чат Бот", "просто Чат Бот"};
                int l =(int)  (Math.random()*name.length);
                String   name1 = name[l];
                return name1;
            case "music":
                return "music";

            case "howareyou":
                String []how = {"Хорошо у тебя ?" ,"Отлично как твои ?","Very good"};
                int ll =(int)  (Math.random()*how.length);
                String   hows = how[ll];
                return hows;


        }
        return simpleChatBot1.sayInReturn(h,kl);
    }



    public  String sayInReturn(String msg, boolean ai) throws Exception {

        SimpleChatBot simpleChatBot = new SimpleChatBot();
        String say = (msg.trim().endsWith("?"))?
                ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
                COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
        if (ai) {
            String message =
                    String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
            for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
                pattern = Pattern.compile(o.getKey());

                if (pattern.matcher(message).find()){
                    if (o.getValue().equals("whattime")){ return date.toString();}


                    else return simpleChatBot.getPatterns( o.getValue());   //в метод передаю значение по ключу
                }

            }
        }
        return say;
    }

}
