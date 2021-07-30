package com.dmos.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

/**
 * PAM Application Configuration
 *
 * <p> Properties are configured in the application.properties file. </p>
 * <p> This class also loads properties that are specific to the active environment specified in the
 * spring.profiles.active property and provided in the application-[env].properties files.</p>
 */

@Component
@ConfigurationProperties("pam")
@Slf4j
public class DmosConfiguration {

	@Value("${spring.profiles.active:}")
	private String activeProfiles;

	private Set<Profile>activeProfileSet = null;

	public enum Profile
	{
		LOCAL,
		TEST,
		DEV,
		STAGE,
		SANDBOX,
		PREPROD,
		BETA,
		PROD
	}

	public String getActiveProfiles(){ return activeProfiles;}

	public Set<Profile>activeProfiles(){
		if ( activeProfileSet == null){
			activeProfileSet = new HashSet<>();
			if (activeProfiles != null && !activeProfiles.isBlank()){
				String[]profiles = activeProfiles.split(",");
				for (String profile : profiles){
					try {
						Profile p = Profile.valueOf(profile.toUpperCase());
						activeProfileSet.add(p);
					} catch (Exception e) {
						log.warn("Profile {} is unknown to the DMOS Configuration.", profile);					}
				}
			}
		}
		return activeProfileSet;
	}

	public boolean profileIsActive(String profileName){
		if (activeProfiles == null || activeProfiles.isBlank() || profileName == null || profileName.isBlank()){
			return false;
		}
		return activeProfiles.toLowerCase().contains(profileName.toLowerCase());
	}

	public boolean profileIsActive(Profile profile){
		return profileIsActive(profile.name());
	}

	public boolean anyProfileActive(Set<Profile> profiles){
		if (profiles == null || profiles.isEmpty()){
			return false;
		}
		boolean active = false;
		for ( Profile profile : profiles){
			active = profileIsActive(profile) || active;
		}
		return active;
	}

}
