package minigameworlds.cardmatching.field.blind;

import minigameworlds.cardmatching.game.GameView;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import minigameworlds.cardmatching.field.create.FieldCreateView;

public class FieldBlindView extends BukkitRunnable implements Listener {
    FieldCreateView view;
    static int count;
    Player player;

    Inventory tempInventory;
    Inventory inventory;

    public FieldBlindView(FieldCreateView view) {
        count = 0;
        this.view = view;
        this.tempInventory = view.getBlindField();
        this.inventory = view.getField();
        this.player = view.getPlayer();
    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent event) {
        if(event.getCurrentItem() == null)
            return;
        
        ItemStack item = event.getCurrentItem().clone();

        if(item != null) {
            event.setCursor(item);
            event.setCurrentItem(null);
        }
    }

    @Override
    public void run() {
        ItemStack stone = new ItemStack(Material.STONE);

        if (count < 7)
            tempInventory.setItem(count + 1, inventory.getItem(count + 1));

        if (count > 0 && count < 8)
            tempInventory.setItem(count + 9, inventory.getItem(count + 9));

        if (count > 1 && count < 9)
            tempInventory.setItem(count + 17, inventory.getItem(count + 17));

        if (count > 2 && count < 10)
            tempInventory.setItem(count + 25, inventory.getItem(count + 25));

        if (count > 3 && count < 11) {
            if (tempInventory.getItem(count - 3) != null)
                tempInventory.setItem(count - 3, stone);

            tempInventory.setItem(count + 33, inventory.getItem(count + 33));
        }

        if (count > 4 && count < 12) {
            if (tempInventory.getItem(count + 5) != null)
                tempInventory.setItem(count + 5, stone);

            tempInventory.setItem(count + 41, inventory.getItem(count + 41));
        }

        if (count > 5 && count < 13)
            if (tempInventory.getItem(count + 13) != null)
                tempInventory.setItem(count + 13, stone);

        if (count > 6 && count < 14)
            if (tempInventory.getItem(count + 21) != null)
                tempInventory.setItem(count + 21, stone);

        if (count > 7 && count < 15)
            if (tempInventory.getItem(count + 29) != null)
                tempInventory.setItem(count + 29, stone);

        if (count > 8 && count < 16)
            if (tempInventory.getItem(count + 37) != null)
                tempInventory.setItem(count + 37, stone);

        player.openInventory(tempInventory);

        count++;

        if (count >= 16) {
            new GameView(tempInventory, inventory, player);
            cancel();
            HandlerList.unregisterAll(this);
        }
    }
}