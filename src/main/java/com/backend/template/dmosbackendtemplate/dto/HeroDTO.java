package com.backend.template.dmosbackendtemplate.dto;

import com.backend.template.dmosbackendtemplate.domain.Hero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class HeroDTO
{
    private long heroId;
    private String heroName;

    public HeroDTO(@NotNull Hero hero)
    {
        this.heroId = hero.getHeroId();
        this.heroName = hero.getHeroName();
    }
}
