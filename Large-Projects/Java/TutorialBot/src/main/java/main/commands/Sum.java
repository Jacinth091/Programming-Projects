package main.commands;

import main.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Sum implements ICommand {

    @Override
    public String getName() {
        return "sum";
    }

    @Override
    public String getDescription() {
        return "Gives the sum of two integers.";
    }

    @Override
    public List<OptionData> getOptions() {
        List <OptionData> data = new ArrayList<>();
        data.add(
                new OptionData(
                OptionType.INTEGER,
                "integer1" ,
                "The first integer",
                true)
                .setMinValue(1)
                .setMaxValue(100)
        );
        data.add(
            new OptionData(
            OptionType.INTEGER,
            "integer2" ,
            "The second integer",
            true)
            .setMinValue(1)
            .setMaxValue(100)
        );

        return data;
    }

    @Override
    public void executeCommand(SlashCommandInteraction event) {
//        if(!event.getName().equals("sum")) return;
        OptionMapping integer1 = event.getOption("integer1");
        int num1 = integer1.getAsInt();

        OptionMapping integer2 = event.getOption("integer2");
        int num2 = 1;
        if(integer2 != null){
            num2 = integer2.getAsInt();
        }

        int result = num1 + num2;

        event.reply("The answer is " + result + " Dipshit!").queue();



    }
}
