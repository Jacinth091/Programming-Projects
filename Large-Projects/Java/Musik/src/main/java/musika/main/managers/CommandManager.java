package musika.main.managers;

import musika.main.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    private List<ICommand> commands = new ArrayList<>();

    @Override
    public void onReady(ReadyEvent event) {
        for(Guild guilds : event.getJDA().getGuilds()){
            for(ICommand command : commands){

                if(command.getOptions() == null){
                    guilds.upsertCommand(command.getName(), command.getDescription()).queue();

                }
                else{
                    guilds.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();

                }
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        for(ICommand command : commands){
            if(command.getName().equals(event.getName())) {
                command.executeCommand(event);
                return;
            }
        }
    }

    public void addCommand(ICommand command){
        commands.add(command);
    }
}
