package us.mcpvpmod.game.stats;

import us.mcpvpmod.game.vars.Vars;

public class StatsSab extends Stats {

	public static String role = "Innocent";
	public static String map = "";
	public static int playersLeft = 0;
	public static int sabsLeft = 0;
	public static boolean detectiveAlive = true;
	
	public static void reset() {
		Vars.put("role", "");
		Vars.put("detective", "");
		Vars.put("winner", "");
		role = "Innocent";
		map = "";
		playersLeft = 0;
		sabsLeft = 0;
		detectiveAlive = true;
	}
	
}
