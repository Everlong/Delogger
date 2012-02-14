package uk.co.jordanmcqueen.Delogger;
//Jordan McQueen -- Everlong -- www.technical-stop.net
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Delogger extends JavaPlugin {
	public static Delogger plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final MyBlockListener blockListener = new MyBlockListener(this);
	
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " is now disabled.");
	}
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is now enabled.");
		this.logger.info(pdfFile.getName() + " Developed by Everlong.");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.blockListener, this);
	}
}
