package com.sinetangent.evade.skill;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * スキルの基底クラス。
 * <p>
 * 数値だけで効果が決まるスキル(回避性能など)は、このクラスをそのまま new して使う。
 * 発火が必要なスキルは継承して、必要な on〜 メソッドだけオーバーライドする。
 * <p>
 * 言語ファイルのキー:
 * <ul>
 *   <li>skill.evade.(id)       … スキル名</li>
 *   <li>skill.evade.(id).desc  … 説明文(改行は \n で複数行にできる)</li>
 * </ul>
 */
public class Skill {

    private final String id;
    private final int maxLevel;
    private final int color;
    private Skill maxLevelBonusSource;

    /**
     * @param id       スキルID(言語ファイルのキーに使う)
     * @param maxLevel 最大レベル(1のときは説明文にレベル表記を出さない)
     * @param color    スキル名と説明文の文字色(0xRRGGBB)
     */
    public Skill(String id, int maxLevel, int color) {
        this.id = id;
        this.maxLevel = maxLevel;
        this.color = color;
    }

    /**
     * 「別のスキルを持っていると、このスキルの最大レベルが上がる」設定をする。
     * 例: 回避性能は、回避者を持っていると最大レベルが +1 される。
     */
    public Skill raisedMaxLevelBy(Skill source) {
        this.maxLevelBonusSource = source;
        return this;
    }

    public String getId() {
        return id;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    /** 装備全体の合計レベル(rawLevels)を踏まえた、実際に適用される最大レベル */
    public int getEffectiveMaxLevel(Map<Skill, Integer> rawLevels) {
        if (maxLevelBonusSource == null) {
            return maxLevel;
        }
        int bonus = Math.min(rawLevels.getOrDefault(maxLevelBonusSource, 0), maxLevelBonusSource.getMaxLevel());
        return maxLevel + bonus;
    }

    // ------------------------------------------------------------------
    // 説明文
    // ------------------------------------------------------------------

    /** 名前行 + 説明文(複数行)。全行が同じ色になる。 */
    public List<Component> getTooltip(int level) {
        Style style = Style.EMPTY.withColor(color);
        List<Component> lines = new ArrayList<>();

        MutableComponent title = Component.translatable("skill.evade." + id);
        if (maxLevel > 1) {
            title.append(" " + toRoman(level));
        }
        lines.add(title.withStyle(style));

        // ツールチップは1行=1Componentなので、\n で区切って行ごとに分ける
        String description = Language.getInstance().getOrDefault("skill.evade." + id + ".desc");
        for (String line : description.split("\n")) {
            lines.add(Component.literal(line).withStyle(style));
        }
        return lines;
    }

    private static String toRoman(int n) {
        String[] roman = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return n >= 0 && n < roman.length ? roman[n] : String.valueOf(n);
    }

    // ------------------------------------------------------------------
    // 発火ポイント(必要なものだけオーバーライドする)
    // ------------------------------------------------------------------

    /**
     * 攻撃時。このスキルの持ち主が攻撃者のとき、ダメージが確定する直前に呼ばれる。
     * event.setAmount(...) でダメージを変更できる。
     */
    public void onAttack(LivingEntity attacker, LivingEntity target, LivingHurtEvent event, int level) {
    }

    /**
     * 被ダメージ時(ダメージ量の変更用)。このスキルの持ち主がダメージを受けるとき、確定する直前に呼ばれる。
     */
    public void onHurt(LivingEntity victim, LivingHurtEvent event, int level) {
    }

    /**
     * 被攻撃時(ダメージ無効化用)。ダメージ処理が始まる前に呼ばれる。
     * event.setCanceled(true) で、ダメージ・ノックバック・被弾モーションをすべて無かったことにできる。
     */
    public void onAttacked(LivingEntity victim, LivingAttackEvent event, int level) {
    }

    /** 定期実行。サーバー側で、1秒(20tick)に1回呼ばれる。 */
    public void onTick(Player player, int level) {
    }
}
