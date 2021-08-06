package com.backend.template.dmosbackendtemplate;


import com.backend.template.dmosbackendtemplate.dto.HeroDTO;
import com.backend.template.dmosbackendtemplate.service.HeroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Transactional
public class DmosBackendTemplateApplicationTests
{

    @Inject
    private PlatformTransactionManager platformTransactionManager;

    protected TransactionTemplate transactionTemplate;

    @Before
    public void setupDmosBackendTemplateApplicationTests(){
        transactionTemplate = new TransactionTemplate(this.platformTransactionManager);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }


    @Autowired
    private HeroService heroService;

    @Test
    public void getAllHeroes()
    {
        List<HeroDTO> results = heroService.getAllHeroes();
        assertEquals(results.size(), 4);
    }

}
