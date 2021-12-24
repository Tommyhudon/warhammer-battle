package com.whbattle.springboot.usecase.effect.dto.dto;

import com.whbattle.springboot.domain.entity.TemporaryModifier.effect.Effect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EffectMapper {
    public Effect mapEffect(EffectDto effectDto) {
        return new Effect(
                effectDto.toHitModifier,
                effectDto.toWoundModifier,
                effectDto.braveryModifier,
                effectDto.saveModifier,
                effectDto.startTurn,
                effectDto.endTurn
        );
    }

    public List<Effect> mapEffectList(EffectDto[] effectDtos) {
        List<Effect> effects = new ArrayList<>();

        if (effectDtos == null) return effects;

        for (EffectDto effectDto : effectDtos) {
            effects.add(this.mapEffect(effectDto));
        }

        return effects;
    }
}
