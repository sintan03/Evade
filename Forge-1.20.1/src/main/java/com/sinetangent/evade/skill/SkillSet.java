package com.sinetangent.evade.skill;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * アイテムに付いているスキルの一覧(追加した順に並ぶ。説明文もこの順で表示される)。
 * <pre>
 * new SkillSet()
 *     .add(ModSkills.EVADER, 1)
 *     .add(ModSkills.ATTACK_BOOST, 7)
 * </pre>
 */
public class SkillSet {

    private final Map<Skill, Integer> levels = new LinkedHashMap<>();

    public SkillSet add(Skill skill, int level) {
        if (level > 0) {
            levels.merge(skill, level, Integer::sum);
        }
        return this;
    }

    public Map<Skill, Integer> asMap() {
        return Collections.unmodifiableMap(levels);
    }
}
