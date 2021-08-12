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

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sagemakerruntime.SageMakerRuntimeClient;
import software.amazon.awssdk.services.sagemakerruntime.model.InvokeEndpointRequest;
import software.amazon.awssdk.services.sagemakerruntime.model.InvokeEndpointResponse;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    @Inject
    private HeroService heroService;

    @GetMapping
    @RolesAllowed({ "dmos_read" })
    public List<HeroDTO> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    @GetMapping("/{heroId}")
    @RolesAllowed("dmos_read")
    public HeroDTO getHero(@PathVariable("heroId") Integer heroId) {
        return heroService.getHero(heroId, null);
    }

    @GetMapping("/sentiment")
    @RolesAllowed("dmos_read")
    public String getSentiment() {
        // Set Variables
        String endpointName = "pytorch-inference-2021-08-10-19-25-44-438";
        String contentType = "text/csv";
        String awsCredentialsProfileName = "default";

        // Read payload into a variable
        SdkBytes body = SdkBytes.fromUtf8String("The simplest pleasures in life are the best, and this film is one of them. Combining a rather basic storyline of love and adventure this movie transcends the usual weekend fair with wit and unmitigated charm.");

        // Build an Invocation request object
        InvokeEndpointRequest request = InvokeEndpointRequest.builder().contentType(contentType).body(body)
                .endpointName(endpointName).build();

        // Load credentails into a profile
        ProfileCredentialsProvider profile = ProfileCredentialsProvider.builder().profileName(awsCredentialsProfileName)
                .build();

        // Build AmazonSageMakerRuntime client
        SageMakerRuntimeClient runtime = SageMakerRuntimeClient.builder().credentialsProvider(profile)
                .region(Region.US_EAST_1).build();

        // Perform an inference
        InvokeEndpointResponse result = runtime.invokeEndpoint(request);

        // Print inference result
       return result.body().asUtf8String();
    }

    @PostMapping
    @ApiOperation("Create Hero")
    // @RolesAllowed("dmos_write")
    public HeroDTO createHero(@Valid @NotNull @RequestBody HeroDTO dto) {
        return new HeroDTO(heroService.createHero(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Hero")
    public HeroDTO updateHero(@Valid @NotNull @PathVariable(value = "id") Integer id,
            @Valid @NotNull @RequestBody HeroDTO heroDTO) {
        return new HeroDTO(heroService.updateHero(id, heroDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Hero")
    public void deleteComment(@Valid @NotNull @PathVariable(value = "id") Integer id) {
        heroService.deleteHero(id);
    }
}