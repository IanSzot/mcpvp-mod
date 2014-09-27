package us.mcpvpmod;

import java.util.Timer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.common.MinecraftForge;
import us.mcpvpmod.events.Events;
import us.mcpvpmod.gui.GuiSecondChat;
import us.mcpvpmod.json.ServerJSON;
import us.mcpvpmod.json.StreamJSON;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.modID, name = Main.name, version = Main.version, guiFactory = Main.guiFactory)
public class Main {
	
	public static final String modID = "mcpvp";
	public static final String name = "MCPVP Mod";
	public static final String version = "1.7.10-2.0";
	public static final String guiFactory = "us.mcpvpmod.config.GuiFactory";
	
	@Instance
	public static Main instance = new Main();
	public static Minecraft mc = Minecraft.getMinecraft();
	public static ServerJSON serverJson = new ServerJSON();
	public static GuiSecondChat secondChat = new GuiSecondChat(mc);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		FMLLog.info("[MCPVP] Mod starting up!");
    	
    	// Register all events in the Events class
		MinecraftForge.EVENT_BUS.register(new Events());
		FMLCommonHandler.instance().bus().register(new Events());
    	
    	// Sync all files.
		Sync.sync();
		
		// Create timers.
		Timer timer = new Timer();
		// Update servers every 7 seconds.
		timer.scheduleAtFixedRate(serverJson, 0, 5*1000L);
		timer.scheduleAtFixedRate(new StreamJSON(), 0, 30*1000L);
		
    }
    	
    @EventHandler
	public void init(FMLInitializationEvent e) {
	}
    
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}
    

	
}
