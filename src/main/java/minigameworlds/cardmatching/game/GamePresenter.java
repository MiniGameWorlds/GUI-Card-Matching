package minigameworlds.cardmatching.game;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import minigameworlds.cardmatching.field.create.FieldCreateView;

public class GamePresenter implements Listener {
    private final GameView view;
    private final GameModel model;

    public GamePresenter(Inventory field, int size, Player player, GameView view) {
        model = new GameModel(field, size, player, this);
        this.view = view;
    }

    @EventHandler
    public void onCloseEvent(InventoryCloseEvent event) {
        event.getHandlers().unregister(this);
        model.getPlayer().sendMessage("총 작업량 : " + model.getWorkload());
    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent event) {
        Player player = model.getPlayer();

        if(event.getCurrentItem() == null)
            return;

        ItemStack clickItem = event.getCurrentItem().clone();
        Inventory inventory = event.getInventory();
        event.setCurrentItem(null);

        // [버튼] 다음 라운드 진행
        if(clickItem.getType() == Material.ARROW) {
            event.getHandlers().unregister(this);
            new FieldCreateView(player, getStage());
        }

        // [버튼] 종료
        if(clickItem.getType() == Material.BARRIER) {
            event.getHandlers().unregister(this);
            player.closeInventory();
            return;
        }

        // [버튼] 공기
        if(clickItem.getType() != Material.STONE) {
            event.setCursor(clickItem);
            return;
        }

        // [버튼] 카드
        if(clickItem.getType() == Material.STONE) {
            switch (model.getSelectedItems().size()) {
                case 0 :
                    model.getSelectedItems().push(event.getRawSlot());
                    ItemMeta clickItemMeta = clickItem.getItemMeta();

                    clickItemMeta.addEnchant(Enchantment.LUCK, 1, false);
                    clickItem.setItemMeta(clickItemMeta);

                    event.setCursor(clickItem);
                    return;
                case 1 :
                    if(model.getSelectedItems().peek() == event.getRawSlot()) {
                        event.setCursor(clickItem);
                        return;
                    }
            }
        }

        int firstSelectSlot = model.getSelectedItems().pop();
        int secondSelectSlot = event.getRawSlot();

        if(model.getField().getItem(firstSelectSlot).getType() == model.getField().getItem(secondSelectSlot).getType()) {
            model.setSize(model.getSize() - 2);

            model.setWorkload(model.getWorkload() + (model.getStageSize() / 3));

            event.setCursor(model.getField().getItem(secondSelectSlot));
            inventory.setItem(firstSelectSlot, model.getField().getItem(firstSelectSlot));

            view.setBorder(inventory, Material.GREEN_STAINED_GLASS_PANE);
            view.setExit(inventory, 45);
        }
        else {
            ItemStack stone = new ItemStack(Material.STONE);
            event.setCursor(stone);
            inventory.setItem(firstSelectSlot, stone);

            if(model.getWorkload() > 0)
                model.setWorkload(model.getWorkload() - 1);

            view.setBorder(inventory, Material.RED_STAINED_GLASS_PANE);
            view.setExit(inventory, 45);
        }

        if(model.getSize() <= 0) {
            event.setCursor(model.getField().getItem(secondSelectSlot));
            inventory.setItem(firstSelectSlot, model.getField().getItem(firstSelectSlot));

            view.setBorder(inventory, Material.YELLOW_STAINED_GLASS_PANE);
            view.setExit(inventory, 45);
            view.setNext(inventory, 53);
        }
    }

    public int getStage() {
        if(model.getWorkload() <= 30)
            return 10;
        else if(model.getWorkload() <= 90)
            return 12;
        else if(model.getWorkload() <= 270)
            return 14;
        else if(model.getWorkload() <= 810)
            return 16;

        return 10;
    }
}
