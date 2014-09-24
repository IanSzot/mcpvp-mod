package us.mcpvpmod.gui.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import us.mcpvpmod.MCPVPServer;
import us.mcpvpmod.Main;
import us.mcpvpmod.Server;

public class GuiMCPVP extends GuiScreen {

	public GuiScreen mainMenu;
    public GuiServerList serverList;

	public static HashMap<Integer, String> serverTypes = new HashMap<Integer, String>();
	public static boolean serverSelected;
	public int selected = -1;
	public static String serverType = "mc-hg.com";
	public static String serverRegion = "us";
	
	public GuiButton connectButton = new GuiButton(100, 25, 375, "Connect");
	public GuiButton regionButton = new GuiButton(98, 25, 400, 95, 20, "Region: US");
	public GuiButton refreshButton = new GuiButton(101, 130, 400, 95, 20, "Refresh");
	public GuiButton cancelButton = new GuiButton(99, 25, 425, "Cancel");

	public GuiMCPVP(GuiScreen mainMenu) {
		this.mainMenu = mainMenu;
		initGui();
	}

	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		initGuiButtons();
        //this.serverList = new GuiServerList(Main.mc, 690, this.height, 25, this.height-100, 0, 0);
		this.serverList = new GuiServerList(this, MCPVPServer.getSortedOfType(serverType, serverRegion), 100);
        this.serverList.registerScrollButtons(this.buttonList, 7, 8);

	}

	public void initGuiButtons() {

		// GuiButton(int ID, x, y, w, h, text);

		int y = 40;
		int id = 1;
		
		for (Server server : Server.allServers()) {
			this.buttonList.add(new GuiButton(id, 25, y, server.toString()));
			serverTypes.put(id, server.baseIP());
			y+= 25;
			id++;
		}
		
		this.buttonList.add(regionButton);
		this.buttonList.add(cancelButton);
		this.buttonList.add(refreshButton);
		this.buttonList.add(connectButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (serverTypes.containsKey(button.id)) {
			this.serverType = serverTypes.get(button.id);

			for (Object guiButton : this.buttonList) {
				((GuiButton)guiButton).enabled = true;
			}
			((GuiButton)this.buttonList.get(button.id-1)).enabled = false;
		}

		if (button.id == 98) {
			if (regionButton.displayString == "Region: US") {
				regionButton.displayString = "Region: EU";
				serverRegion = "eu";
			} else if (regionButton.displayString == "Region: EU") {
				regionButton.displayString = "Region: BR";
				serverRegion = "br";
			} else if (regionButton.displayString == "Region: BR") {
				regionButton.displayString = "Region: US";
				serverRegion = "us";
			}
		}
		this.serverList = new GuiServerList(this, MCPVPServer.getSortedOfType(serverType, serverRegion), 100);
		
		if (button.id == 99) this.mc.displayGuiScreen(mainMenu);
		if (button.id == 100) {
			Main.connectToServer(this.serverList.servers.get(selected).Server, (GuiScreen)this, Main.mc);
		}
		
		if (button.id == 101) {
			Main.serverJson.run();
			new GuiServerList(this, MCPVPServer.getSortedOfType(serverType, serverRegion), 100);
		}
		/*
		if (button.id <= 2) {
			String urlStr = "";
			switch (button.id) {
			case 0: urlStr = "http://forum.minecraftpvp.com";
				break;
			case 1: urlStr = "http://twitter.com/minecraft_pvp";
				break;
			case 2: urlStr = "https://www.facebook.com/MCPVP";
				break;
			}
			try {
				URL url = new URL(urlStr);
				URI uri = url.toURI();
				Desktop.getDesktop().browse(uri);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		*/
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		connectButton.enabled = this.selected != -1;

		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.GREEN + "MCPVP Servers", this.width / 2, 15, 16777215);
		this.serverList.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}
	
    /**
     * Draws either a gradient over the background screen (when it exists) or a flat gradient over background.png
     */
    public void drawDefaultBackground()
    {
        this.drawWorldBackground(0);
    }

    public void selectServer(int index) {
    	if (!serverList.servers.get(index).MOTD.contains("Server Offline :'(")) this.selected = index;
    }
    
	public boolean isServerSelected(int index) {
		return selected == index;
	}

}
