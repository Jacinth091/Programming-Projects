package main.commands;

import main.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class Embed implements ICommand {
    @Override
    public String getName() {
        return "embed";
    }

    @Override
    public String getDescription() {
        return "Sends an embedded message";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void executeCommand(SlashCommandInteraction event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Hello Mga Yawa!");
        embedBuilder.setDescription("Discord Announcement");
        embedBuilder.setAuthor("Admin Gwapo");
        embedBuilder.addField("Field 1", "value eyy", false);
        embedBuilder.addField("Field 2", "value eyy", false);
        embedBuilder.addField("Field 3", "value eyy", false);
        embedBuilder.addField("Field 4", "value eyy", false);
        embedBuilder.setThumbnail("https://i.kym-cdn.com/photos/images/newsfeed/002/601/167/c81");
        embedBuilder.setImage("https://i.pinimg.com/474x/6f/9f/5b/6f9f5b3723baa2fb24477fc82778221e.jpg");
        embedBuilder.setFooter("Goodbye mga Yawa");

        event.replyEmbeds(embedBuilder.build()).queue();



    }
}
