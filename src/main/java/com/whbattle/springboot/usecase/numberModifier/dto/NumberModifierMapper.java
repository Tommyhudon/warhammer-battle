package com.whbattle.springboot.usecase.numberModifier.dto;

import com.whbattle.springboot.domain.entity.TemporaryModifier.numberModifier.NumberModifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NumberModifierMapper {
    public NumberModifier mapNumberBonus(NumberModifierDto numberModifierDto) {
        return new NumberModifier(
                numberModifierDto.minimumNumberForModifier,
                numberModifierDto.braveryModifier,
                numberModifierDto.toHitModifier,
                numberModifierDto.toWoundModifier,
                numberModifierDto.saveModifier);
    }

    public List<NumberModifier> mapNumberBonusList(NumberModifierDto[] numberModifierDtos) {
        List<NumberModifier> numberModifiers = new ArrayList<>();

        if (numberModifierDtos == null) return numberModifiers;

        for (NumberModifierDto numberModifierDto : numberModifierDtos) {
            numberModifiers.add(mapNumberBonus(numberModifierDto));
        }

        return numberModifiers;
    }
}
