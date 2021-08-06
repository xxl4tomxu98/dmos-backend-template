package com.backend.template.dmosbackendtemplate.rest;

import com.backend.template.dmosbackendtemplate.dto.HeroDTO;
import com.backend.template.dmosbackendtemplate.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class HeroController
{

    @Autowired
    private HeroService heroService;

    @GetMapping("")
    //@RolesAllowed({"heroes-user", "heroes-admin"})
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