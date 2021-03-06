package us.eunoians.mcrpg.api.events.mcrpg.unarmed;

import lombok.Getter;
import lombok.Setter;
import us.eunoians.mcrpg.abilities.unarmed.SmitingFist;
import us.eunoians.mcrpg.api.events.mcrpg.AbilityActivateEvent;
import us.eunoians.mcrpg.players.McRPGPlayer;
import us.eunoians.mcrpg.types.AbilityEventType;

public class SmitingFistEvent extends AbilityActivateEvent {

  @Getter
  @Setter
  private int absorptionLevel;

  @Getter
  @Setter
  private double smiteChance;

  @Getter
  @Setter
  private int smiteDuration;

  @Getter
  @Setter
  private boolean removeInvis;

  @Getter
  @Setter
  private boolean removeDebuffs;

  @Getter
  @Setter
  private int duration;

  @Getter
  @Setter
  private int cooldown;

  public SmitingFistEvent(McRPGPlayer player, SmitingFist smitingFist, int absorptionLevel, double smiteChance, int smiteDuration, boolean removeInvis, boolean removeDebuffs, int duration, int cooldown){
    super(smitingFist, player, AbilityEventType.COMBAT);
    this.absorptionLevel = absorptionLevel;
    this.smiteChance = smiteChance;
    this.smiteDuration = smiteDuration;
    this.removeInvis = removeInvis;
    this.removeDebuffs = removeDebuffs;
    this.duration = duration;
    this.cooldown = cooldown;
  }
}
