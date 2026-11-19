package com.sinetangent.evade.skill;

import com.sinetangent.evade.skill.skills.AttackBoostSkill;
import com.sinetangent.evade.skill.skills.EvaderSkill;

/**
 * スキルの一覧。スキルを増やすときはここに追加する。
 * 文字色は 0xRRGGBB で指定(§7 = 0xAAAAAA、§e = 0xFFFF55)。
 */
public class ModSkills {

    private static final int GRAY = 0xAAAAAA;
    private static final int YELLOW = 0xFFFF55;

    // 回避者(最大レベル1なのでレベル表記は出ない)。他のスキルから参照されるので最初に定義する
    public static final Skill EVADER = new EvaderSkill(YELLOW);

    public static final Skill ATTACK_BOOST = new AttackBoostSkill(GRAY);

    // 回避性能・回避距離: 最大IV。回避者を持っていると最大Vになる
    public static final Skill EVADE_WINDOW = new Skill("evade_window", 4, GRAY).raisedMaxLevelBy(EVADER);
    public static final Skill EVADE_DISTANCE = new Skill("evade_distance", 4, GRAY).raisedMaxLevelBy(EVADER);

    // 回避再使用: 最大III
    public static final Skill DODGE_RECOVERY = new Skill("dodge_recovery", 3, GRAY);
}
