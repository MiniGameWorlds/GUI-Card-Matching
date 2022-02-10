package minigameworlds.cardmatching.field.create;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FieldCreateModel  {
    private FieldCreatePresenter presenter;

    public FieldCreateModel(FieldCreatePresenter presenter) {
        this.presenter = presenter;
    }

    private Food[] whiteStageFoods = {
            new Food(Material.APPLE),
            new Food(Material.BREAD),
            new Food(Material.PORKCHOP),
            new Food(Material.COOKED_BEEF),
            new Food(Material.MELON_SLICE)
    };

    private Food[] blueStageFoods = {
            new Food(Material.APPLE),
            new Food(Material.BREAD),
            new Food(Material.MELON_SLICE),
            new Food(Material.PORKCHOP),
            new Food(Material.COOKED_BEEF),
            new Food(Material.SWEET_BERRIES),
            new Food(Material.CARROT)
    };

    private Food[] purpleStageFoods = {
            new Food(Material.APPLE),
            new Food(Material.APPLE),
            new Food(Material.BREAD),
            new Food(Material.BREAD),
            new Food(Material.MELON_SLICE),
            new Food(Material.MELON_SLICE),
            new Food(Material.PORKCHOP),
            new Food(Material.COOKED_BEEF),
            new Food(Material.SWEET_BERRIES),
            new Food(Material.CARROT),
    };

    private Food[] goldStageFoods = {
            new Food(Material.APPLE),
            new Food(Material.APPLE),
            new Food(Material.BREAD),
            new Food(Material.BREAD),
            new Food(Material.MELON_SLICE),
            new Food(Material.MELON_SLICE),
            new Food(Material.PORKCHOP),
            new Food(Material.PORKCHOP),
            new Food(Material.COOKED_BEEF),
            new Food(Material.COOKED_BEEF),
            new Food(Material.SWEET_BERRIES),
            new Food(Material.SWEET_BERRIES),
            new Food(Material.CARROT),
            new Food(Material.CARROT),
    };

    public Food[] getWhiteStageFoods() {
        return whiteStageFoods;
    }

    public Food[] getBlueStageFoods() {
        return blueStageFoods;
    }

    public Food[] getPurpleStageFoods() {
        return purpleStageFoods;
    }

    public Food[] getGoldStageFoods() {
        return goldStageFoods;
    }
}

class Food {
    ItemStack food;
    int count = 0;
    public Food(Material material) {
        this.food = new ItemStack(material);
    }

    public ItemStack getFood() {
        count++;
        return food;
    }
}