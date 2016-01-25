package io.github.CMD_BLOCK.RememberHighestOnline;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public void onEnable(){
		if(!getDataFolder().exists()){
			getDataFolder().mkdir();
		}
		File file=new File(getDataFolder(),"config.yml");
		if(!(file.exists())){
			this.saveDefaultConfig();
		}
		this.reloadConfig();
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("RememberHighestOnline������سɹ������ߣ�CMDؼBLOCK");
	}
	public boolean onCommand(CommandSender sender,Command cmd, String lable, String[] args){
		if(lable.equalsIgnoreCase("rho")){
			if(sender.hasPermission("rho.use")||sender.isOp()){
				this.reloadConfig();
				sender.sendMessage("��a���سɹ�");
				return true;
			}
			sender.sendMessage("��4��û��ʹ�ô������Ȩ��");
			return true;
		}
		return false;
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		int online = Bukkit.getServer().getOnlinePlayers().size();
		int Highest = this.getConfig().getInt("Highest");
		String msg = this.getConfig().getString("Message").replaceAll("%max%",online+"");
		if(online>Highest && this.getConfig().getBoolean("sendMessageToAllPlayer")){
			Bukkit.broadcastMessage(msg);
		}
		this.getConfig().set("Highest", online);
		this.saveConfig();
	}
}
