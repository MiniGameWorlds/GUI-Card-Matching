package minigameworlds.cardmatching.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import minigameworlds.cardmatching.Main;

public class GameView {
    GamePresenter presenter;

    public GameView(Inventory tempInventory, Inventory field, Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.GOLD + "작업량 : " + GameModel.workload);
        inventory.setContents(tempInventory.getContents());
        int size = getFieldSize(inventory);

        setBorder(inventory, Material.BLACK_STAINED_GLASS_PANE);
        setExit(inventory, 45);

        player.openInventory(inventory);

        presenter = new GamePresenter(field, size, player, this);
        Bukkit.getServer().getPluginManager().registerEvents(presenter, Main.plugin);
    }

    public int getFieldSize(Inventory inventory) {
        int size = 0;

        for(int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) != null)
                size++;
        }
        return size;
    }

    public Inventory setExit(Inventory inventory, int slot) {
        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemMeta meta = exit.getItemMeta();

        meta.setDisplayName(ChatColor.RESET+ "" +ChatColor.RED + "종료");
        exit.setItemMeta(meta);

        inventory.setItem(slot, exit);

        return inventory;
    }

    public Inventory setNext(Inventory inventory, int slot) {
        ItemStack next = new ItemStack(Material.ARROW);
        ItemMeta meta = next.getItemMeta();

        meta.setDisplayName(ChatColor.RESET+ "" +ChatColor.RED + "다음 라운드");
        next.setItemMeta(meta);

        inventory.setItem(slot, next);

        return inventory;
    }

    public Inventory setBorder(Inventory inventory, Material borderMaterial) {
        ItemStack borderItem = new ItemStack(borderMaterial);

        for(int i = 0; i < inventory.getSize(); i++) {
            switch (i%9) {
                case 0:
                case 8:
                    inventory.setItem(i, borderItem);
                    continue;
            }
        }

        return inventory;
    }
}
