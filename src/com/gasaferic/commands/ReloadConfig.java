package com.gasaferic.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import com.gasaferic.main.Main;

public class ReloadConfig implements CommandExecutor, Listener {
	
	private Main plugin = Main.getInstance();
	
	private String prefix = plugin.getPrefixString("prefix");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
	Player player = (Player) sender;
    if (cmd.getName().equalsIgnoreCase("rustreload")) {
        for (int i=0; i<1;i++)
        this.plugin.reloadConfig();
        player.sendMessage(prefix + "§aHai ricaricato il config con successo");
        return true;


	}
	return false;
}

}