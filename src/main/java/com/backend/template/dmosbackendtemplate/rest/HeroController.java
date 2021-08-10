package com.backend.template.dmosbackendtemplate.rest;

import com.backend.template.dmosbackendtemplate.dto.HeroDTO;
import com.backend.template.dmosbackendtemplate.service.HeroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class HeroController
{

    @Inject
    private HeroService heroService;

    @GetMapping("")
    @RolesAllowed({"dmos_user", "view-profile"})
    public List<HeroDTO> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    @GetMapping("/{heroId}")
    //@RolesAllowed("heroes-admin")
    public HeroDTO getHero(@PathVariable("heroId") Long heroId)
    {
        return heroService.getHero(heroId, null);
    }
}