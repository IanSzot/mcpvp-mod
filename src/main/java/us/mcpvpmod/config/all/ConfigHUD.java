package us.mcpvpmod.config.all;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import us.mcpvpmod.util.Format;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.Loader;


public class ConfigHUD extends DummyModContainer {

	public static boolean renderBG = true;
	public static int margin = 3;
	public static boolean centerTitles = true;
	public static boolean alignWidths = false;
	public static boolean alignHeights = false;
	public static int medalTimer = 7;
	public static boolean showArmor;
	public static boolean showPotion;
	public static String armorMode;
	public static String armorPosition;
	public static String potionMode;
	public static String potionPosition;
	public static boolean alignItems = true;
	
    public static String fileName = "mcpvp_hud.cfg";
    
    private static Configuration config;

    public ConfigHUD() {
        config = null;
        File cfgFile = new File(Loader.instance().getConfigDir(), fileName);
        config = new Configuration(cfgFile);

        syncConfig();
    }
    
    public static Configuration getConfig() {
        if (config == null) {
            File cfgFile = new File(Loader.instance().getConfigDir(), fileName);
            config = new Configuration(cfgFile);
        }

        syncConfig();
        return config;
    }
    
    
    public static void syncConfig() {
        if (config == null) {
            File cfgFile = new File(Loader.instance().getConfigDir(), fileName);
            config = new Configuration(cfgFile);
        }
    	
        List<String> propOrder = new ArrayList<String>();
        
        Property prop;

        prop = config.get(CATEGORY_GENERAL, "showArmor", true);
        prop.setLanguageKey("config.hud.showArmor");
    	showArmor = prop.getBoolean();
    	propOrder.add(prop.getName());
        
        prop = config.get(CATEGORY_GENERAL, "armorMode", "Show Durability Remaining", Format.s("config.hud.armorMode"), new String[]{"Show Durability Remaining", "Show Durability Remaining out of Total", "Don't show Durability Remaining"});
        prop.setLanguageKey("config.hud.armorMode");
    	armorMode = prop.getString();
    	propOrder.add(prop.getName());
    	
        prop = config.get(CATEGORY_GENERAL, "armorPosition", "Left of the Armor", Format.s("config.hud.armorPosition"), new String[]{"Left of the Armor", "Right of the Armor"});
        prop.setLanguageKey("config.hud.armorPosition");
    	armorPosition = prop.getString();
    	propOrder.add(prop.getName());
    	
        prop = config.get(CATEGORY_GENERAL, "showPotion", true);
        prop.setLanguageKey("config.hud.showPotion");
    	showPotion = prop.getBoolean();
    	propOrder.add(prop.getName());
    	
        prop = config.get(CATEGORY_GENERAL, "potionMode", "Show Time Remaining", Format.s("config.hud.potionMode"), new String[]{"Show Time Remaining", "Don't Show Time Remaining"});
        prop.setLanguageKey("config.hud.potionMode");
    	potionMode = prop.getString();
    	propOrder.add(prop.getName());
    	
        prop = config.get(CATEGORY_GENERAL, "potionPosition", "Left of the Potions", Format.s("config.hud.armorPosition"), new String[]{"Left of the Potions", "Right of Potions"});
        prop.setLanguageKey("config.hud.potionPosition");
    	potionPosition = prop.getString();
    	propOrder.add(prop.getName());
        
    	prop = config.get(CATEGORY_GENERAL, "renderBG", true);
        prop.setLanguageKey("config.hud.renderBG");
    	renderBG = prop.getBoolean();
    	propOrder.add(prop.getName());
    	
    	prop = config.get(CATEGORY_GENERAL, "margin", 3, "The space between display blocks.", 0, 1000);
        prop.setLanguageKey("config.hud.margin");
    	margin = prop.getInt();
    	propOrder.add(prop.getName());
    	
    	prop = config.get(CATEGORY_GENERAL, "centerTitles", true);
        prop.setLanguageKey("config.hud.centerTitles");
    	centerTitles = prop.getBoolean();
    	propOrder.add(prop.getName());
    	
    	prop = config.get(CATEGORY_GENERAL, "alignItems", true);
        prop.setLanguageKey("config.hud.alignItems");
    	alignItems = prop.getBoolean();
    	propOrder.add(prop.getName());
    	
    	prop = config.get(CATEGORY_GENERAL, "alignWidths", true);
        prop.setLanguageKey("config.hud.alignWidths");
    	alignWidths = prop.getBoolean();
    	propOrder.add(prop.getName());
    	
    	prop = config.get(CATEGORY_GENERAL, "alignHeights", true);
        prop.setLanguageKey("config.hud.alignHeights");
    	alignHeights = prop.getBoolean();
    	propOrder.add(prop.getName());
        
    	prop = config.get(CATEGORY_GENERAL, "medalTimer", 7, "The number of seconds to display a medal for.", 1, 1000);
    	prop.setLanguageKey("config.hud.medalTimer");
    	margin = prop.getInt();
    	propOrder.add(prop.getName());
    	
        config.setCategoryPropertyOrder(CATEGORY_GENERAL, propOrder);

        if (config.hasChanged())
        {
            config.save();
        }
    }
    
}
