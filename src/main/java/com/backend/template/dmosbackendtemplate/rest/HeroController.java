package com.backend.template.dmosbackendtemplate.rest;

import com.backend.template.dmosbackendtemplate.dto.HeroDTO;
import com.backend.template.dmosbackendtemplate.service.HeroService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class HeroController
{

    @Inject
    private HeroService heroService;

    @GetMapping
    @RolesAllowed({"dmos_read"})
    public List<HeroDTO> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    @GetMapping("/{heroId}")
    @RolesAllowed("dmos_read")
    public HeroDTO getHero(@PathVariable("heroId") Integer heroId)
    {
        return heroService.getHero(heroId, null);
    }

    @PostMapping
    @ApiOperation("Create Hero")
//    @RolesAllowed("dmos_write")
    public HeroDTO createHero(
            @Valid @NotNull @RequestBody HeroDTO dto) {
        return new HeroDTO(heroService.createHero(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Hero")
    public HeroDTO updateHero(
            @Valid @NotNull  @PathVariable(value = "id") Integer id,
            @Valid @NotNull @RequestBody HeroDTO heroDTO) {
        return new HeroDTO(heroService.updateHero(id, heroDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Hero")
    public void deleteComment(
            @Valid @NotNull  @PathVariable(value = "id") Integer id) {
        heroService.deleteHero(id);
    }
}