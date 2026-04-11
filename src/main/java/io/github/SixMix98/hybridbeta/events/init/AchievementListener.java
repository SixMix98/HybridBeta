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
    public static Achievement CRAFT_BOW;
    public static Achievement CRAFT_QUIVER;

    @EventListener
    public void registerAchievements(AchievementRegisterEvent event) {
        HYBRID_ACHIEVEMENT_PAGE = new AchievementPage(InitListener.NAMESPACE.id("HybridBetaAchievements"));
        DIAMONDS = new Achievement(212, "mineDiamond", 0, 0, Item.DIAMOND, null);
        REACH_NETHER = new Achievement(213, "weNeedToGoDeeper", 1, 3, Block.OBSIDIAN, DIAMONDS);
        REDIRECT_GHAST = new Achievement(214, "returnToSender", 3, 4, Item.LAVA_BUCKET, REACH_NETHER).challenge();
        CRAFT_BOOKSHELF = new Achievement(215, "librarian", -2, 0, Block.BOOKSHELF, null);
        CRAFT_BOW = new Achievement(216, "marksman", 2, 0, Item.BOW, null);
        SNIPE_SKELETON = new Achievement(217, "sniperDuel", 4, -1, Item.ARROW, CRAFT_BOW).challenge();
        CRAFT_QUIVER = new Achievement(218, "quiverMeTimbers", 3, 2, ItemListener.QUIVER, CRAFT_BOW);
        event.achievements.add(DIAMONDS);
        event.achievements.add(REACH_NETHER);
        event.achievements.add(REDIRECT_GHAST);
        event.achievements.add(CRAFT_BOOKSHELF);
        event.achievements.add(CRAFT_BOW);
        event.achievements.add(SNIPE_SKELETON);
        event.achievements.add(CRAFT_QUIVER);
        HYBRID_ACHIEVEMENT_PAGE.addAchievements(DIAMONDS, REACH_NETHER, REDIRECT_GHAST, CRAFT_BOOKSHELF, CRAFT_BOW, SNIPE_SKELETON, CRAFT_QUIVER);
    }
}
