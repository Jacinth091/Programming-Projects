package main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.Route;
import org.jetbrains.annotations.NotNull;

public class Listeners extends ListenerAdapter {

/*
    @Override
    public void onReady(ReadyEvent event) {
        Guild mainGuild = event.getJDA().getGuildById(1288048247390208040L);
        mainGuild.upsertCommand("sum", "Gives the sum of two integers").addOptions(
                new OptionData(
                        OptionType.INTEGER,
                        "integer1" ,
                        "The first integer",
                        true)
                        .setMinValue(1)
                        .setMaxValue(100),
                new OptionData(
                        OptionType.INTEGER,
                        "integer2" ,
                        "The second integer",
                        true)
                        .setMinValue(1)
                        .setMaxValue(100)
        ).queue();



    }
*/

/*    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String msgReceived = event.getMessage().getContentRaw();
        System.out.println(msgReceived);
        if(event.getAuthor().isBot()) return;
        MessageChannel channel = event.getChannel();
        channel.sendMessage(msgReceived).queue();
    }*/
}
