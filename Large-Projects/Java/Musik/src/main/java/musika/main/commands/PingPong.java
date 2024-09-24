package musika.main.commands;

import musika.main.ICommand;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class PingPong implements ICommand {
    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Get the server response time.";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }


    @Override
    public void executeCommand(SlashCommandInteraction event) {
        long startTime = System.nanoTime(); // Start time in nanoseconds
        long gatewayPing = event.getJDA().getGatewayPing(); // Discord Ping

        event.deferReply().queue();
        if(event.getUser().isBot()) return;
        if (event.getName().equals("ping")) {
            // Perform the operation

            event.getHook().sendMessage("Pong!").queue(response -> {
                long endTime = System.nanoTime(); // End time in nanoseconds
                long duration = endTime - startTime; // Calculate duration in nanoseconds

                // Convert to milliseconds
                double durationInMs = duration / 1_000_000.0; // Convert to milliseconds
                response.getChannel().sendMessage("Response time: " + durationInMs + " ms\nGateway Ping: " + gatewayPing + " ms").queue();
            });
        }
    }

}
