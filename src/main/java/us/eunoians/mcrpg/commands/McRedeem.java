package us.eunoians.mcrpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.eunoians.mcrpg.McRPG;
import us.eunoians.mcrpg.api.exceptions.McRPGPlayerNotFoundException;
import us.eunoians.mcrpg.api.util.Methods;
import us.eunoians.mcrpg.gui.GUITracker;
import us.eunoians.mcrpg.gui.RedeemStoredGUI;
import us.eunoians.mcrpg.players.McRPGPlayer;
import us.eunoians.mcrpg.players.PlayerManager;
import us.eunoians.mcrpg.types.Skills;

public class McRedeem implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    McRPG plugin = McRPG.getInstance();
    if(sender instanceof Player){
      Player p = (Player) sender;
      if(PlayerManager.isPlayerFrozen(p.getUniqueId())){
        return true;
      }
      String world = p.getWorld().getName();
      //Disabled Worlds
      if(McRPG.getInstance().getConfig().contains("Configuration.DisabledWorlds") &&
              McRPG.getInstance().getConfig().getStringList("Configuration.DisabledWorlds").contains(world)) {
        return true;
      }
      else if(plugin.getConfig().getBoolean("Configuration.UseRedeemPerm") && !(p.hasPermission("mcrpg.*") || p.hasPermission("mcrpg.redeem"))){
        p.sendMessage(Methods.color(plugin.getPluginPrefix() + plugin.getLangFile().getString("Messages.Commands.Utility.NoPerms")));
        return true;
      }
      // /mcredeem %skill%
      if(args.length < 1){
        p.sendMessage(Methods.color(plugin.getPluginPrefix() + plugin.getLangFile().getString("Messages.Commands.Utility.HelpPrompt").replace("<command>", "mcredeem")));
        return true;
      }
      else{
        if(!Skills.isSkill(args[0])){
          p.sendMessage(Methods.color(plugin.getPluginPrefix() + plugin.getLangFile().getString("Messages.Commands.Utility.NotASkill")));
          return true;
        }
        else{
          Skills skill = Skills.fromString(args[0]);
          McRPGPlayer mp;
          try{
            mp = PlayerManager.getPlayer(p.getUniqueId());
          }
          catch(McRPGPlayerNotFoundException exception){
            return true;
          }
          RedeemStoredGUI redeemStoredGUI = new RedeemStoredGUI(mp, skill);
          p.openInventory(redeemStoredGUI.getGui().getInv());
          GUITracker.trackPlayer(p, redeemStoredGUI);
          return true;
        }
      }
    }
    else{
      sender.sendMessage(Methods.color(plugin.getPluginPrefix() + plugin.getConfig().getString("Messages.Commands.Utility.OnlyPlayers")));
      return true;
    }
  }
}
