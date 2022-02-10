package minigameworlds.cardmatching;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import minigameworlds.cardmatching.menu.MenuView;

public class Main extends JavaPlugin {
    public static Plugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getCommand("cm").setExecutor(new MenuView(plugin));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
