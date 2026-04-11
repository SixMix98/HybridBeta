package io.github.SixMix98.hybridbeta.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;

public class AchievementListener {
    public static AchievementPage HYBRID_ACHIEVEMENT_PAGE;
    public static Achievement DIAMONDS;
    public static Achievement REACH_NETHER;
    public static Achievement REDIRECT_GHAST;
    public static Achievement SNIPE_SKELETON;
    public static Achievement CRAFT_BOOKSHELF;

    @EventListener
    public void registerAchievements(AchievementRegisterEvent event) {
        HYBRID_ACHIEVEMENT_PAGE = new AchievementPage(InitListener.NAMESPACE.id("HybridBetaAchievements"));
        DIAMONDS = new Achievement(212, "mineDiamond", 0, 0, Item.DIAMOND, null);
        REACH_NETHER = new Achievement(213, "weNeedToGoDeeper", 1, 3, Block.OBSIDIAN, DIAMONDS);
        REDIRECT_GHAST = new Achievement(214, "returnToSender", 3, 2, Item.FLINT_AND_STEEL, REACH_NETHER);
        SNIPE_SKELETON = new Achievement(215, "sniperDuel", 2, 0, Item.BOW, null);
        CRAFT_BOOKSHELF = new Achievement(216, "librarian", -2, 0, Block.BOOKSHELF, null);
        event.achievements.add(DIAMONDS);
        event.achievements.add(REACH_NETHER);
        event.achievements.add(REDIRECT_GHAST);
        event.achievements.add(SNIPE_SKELETON);
        event.achievements.add(CRAFT_BOOKSHELF);
        HYBRID_ACHIEVEMENT_PAGE.addAchievements(DIAMONDS, REACH_NETHER, REDIRECT_GHAST, SNIPE_SKELETON, CRAFT_BOOKSHELF);
    }
}
