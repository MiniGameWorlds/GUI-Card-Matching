package minigameworlds.cardmatching.field.create;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Stack;

public class FieldCreatePresenter {
    FieldCreateView view;
    FieldCreateModel data;

    public FieldCreatePresenter(FieldCreateView view) {
        this.view = view;
        this.data = new FieldCreateModel(this);
    }

    public Stack<ItemStack> getMixedFoods(Food[] items) {
        Stack<ItemStack> temp = new Stack<>();
        for(int i = 0; i < (items.length * 2);) {
            int select = (int) (Math.random() * items.length);
            if(items[select].count < 2) {
                temp.add(items[select].getFood());
                i++;
            }
        }

        return temp;
    }

    public Inventory createWhiteStageInventory() {
        Inventory field = Bukkit.createInventory(null, 54, ChatColor.GOLD + "난이도 : "+ ChatColor.WHITE + "쉬움");

        Food[] items = data.getWhiteStageFoods();
        Stack<ItemStack> stack = getMixedFoods(items);

        Stack<ItemStack> tempStack = (Stack<ItemStack>) stack.clone();

        for(int i = 0; i < 5; i++) {
            field.setItem((20 + i), tempStack.pop());
            field.setItem((29 + i), tempStack.pop());
        }

        return field;
    }

    public Inventory createBlueStageInventory() {
        Inventory field = Bukkit.createInventory(null, 54, ChatColor.GOLD + "난이도 : "+ ChatColor.BLUE + "보통" );

        Food[] items = data.getBlueStageFoods();
        Stack<ItemStack> stack = getMixedFoods(items);

        Stack<ItemStack> tempStack = (Stack<ItemStack>) stack.clone();

        for(int i = 0; i < 7; i++) {
            field.setItem((19 + i), tempStack.pop());
            field.setItem((28 + i), tempStack.pop());
        }

        return field;
    }

    public Inventory createPurpleStageInventory() {
        Inventory field = Bukkit.createInventory(null, 54, ChatColor.GOLD + "난이도 : "+ ChatColor.LIGHT_PURPLE + "어려움");

        Food[] items = data.getPurpleStageFoods();
        Stack<ItemStack> stack = getMixedFoods(items);

        Stack<ItemStack> tempStack = (Stack<ItemStack>) stack.clone();

        for(int i = 0; i < 5; i++) {
            field.setItem((11 + i), tempStack.pop());
            field.setItem((20 + i), tempStack.pop());
            field.setItem((29 + i), tempStack.pop());
            field.setItem((38 + i), tempStack.pop());
        }

        return field;
    }

    public Inventory createGoldStageInventory() {
        Inventory field = Bukkit.createInventory(null, 54, ChatColor.GOLD + "난이도 : "+ ChatColor.GOLD + "매우 어려움");

        Food[] items = data.getGoldStageFoods();
        Stack<ItemStack> stack = getMixedFoods(items);

        Stack<ItemStack> tempStack = (Stack<ItemStack>) stack.clone();

        for(int i = 0; i < 7; i++) {
            field.setItem((10 + i), tempStack.pop());
            field.setItem((19 + i), tempStack.pop());
            field.setItem((28 + i), tempStack.pop());
            field.setItem((37 + i), tempStack.pop());
        }

        return field;
    }
}
