package musika.main.commands;

import musika.main.ICommand;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Play implements ICommand {
    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "Play music with a query.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.STRING,
                "query",
                "Input query to search for music.",
                true));

        return null;
    }

    @Override
    public void executeCommand(SlashCommandInteraction event) {
        String musicQuery = event.getOption("query").getAsString();
    }
}
