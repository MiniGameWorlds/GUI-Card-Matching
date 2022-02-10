package minigameworlds.cardmatching.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;


public class MenuView implements CommandExecutor {
    MenuPresenter presenter;
    Player player;
    Inventory menuUI;
    Plugin plugin;

    public MenuView(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("플레이어만 접근 가능한 명령어입니다.");
            return false;
        }
        player = (Player) sender;
        presenter = new MenuPresenter(menuUI, player, this);
        showMenu(player);
        return true;
    }

    public void showMenu(Player player) {
        menuUI = Bukkit.createInventory(null, 27, ChatColor.GOLD + "카드 맞추기");

        plugin.getServer().getPluginManager().registerEvents(presenter, plugin);

        presenter.setBorder(menuUI, Material.GRAY_STAINED_GLASS_PANE);
        presenter.setPlayerStatus(menuUI, 4);

        presenter.setStage(menuUI);
        presenter.setExit(menuUI,18);

        player.openInventory(menuUI);
    }
}
