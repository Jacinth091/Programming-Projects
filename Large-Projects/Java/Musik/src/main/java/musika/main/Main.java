package musika.main;

import io.github.cdimascio.dotenv.Dotenv;
import musika.main.commands.PingPong;
import musika.main.managers.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;



public class Main {
    public static void main(String[] args){
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("bot-token");


        JDA jda = JDABuilder.createDefault(token).build();
//        jda.addEventListener(new Listeners());

        CommandManager manager = new CommandManager();
        manager.addCommand(new PingPong());

        jda.addEventListener(manager);
    }

}
