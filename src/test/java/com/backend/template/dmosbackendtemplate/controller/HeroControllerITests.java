package com.backend.template.dmosbackendtemplate.controller;

import com.backend.template.dmosbackendtemplate.DmosBackendTemplateApplicationTests;
import com.backend.template.dmosbackendtemplate.dto.HeroDTO;
import com.backend.template.dmosbackendtemplate.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HeroControllerITests extends DmosBackendTemplateApplicationTests {
    private final List<Long> createdHeroes = new ArrayList<>();
    private HeroDTO heroPrimary;

    @Autowired
    private HeroService heroService;

    /*
    Endpoints
     */

    /*
    Helper Bois
     */
}
