package main;

import io.github.cdimascio.dotenv.Dotenv;
import main.commands.Embed;
import main.commands.PingPong;
import main.commands.Sum;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {
        Dotenv dotenv = Dotenv.load();

        // Get the token from the .env file
        String token = dotenv.get("token");
        JDA jda = JDABuilder.createDefault(token,
                GatewayIntent.GUILD_MESSAGES,  // Needed for receiving guild messages
                GatewayIntent.MESSAGE_CONTENT).build();
//        jda.addEventListener(new Listeners());

        CommandManager manager = new CommandManager();
        manager.addCommand(new Sum());
        manager.addCommand(new PingPong());
        manager.addCommand(new Embed());
        jda.addEventListener(manager);


    }
}
