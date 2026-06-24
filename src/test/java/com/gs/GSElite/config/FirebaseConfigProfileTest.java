package com.gs.GSElite.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import static org.assertj.core.api.Assertions.assertThat;

class FirebaseConfigProfileTest {

    @Test
    void firebaseLocalConfig_isAnnotatedWithLocalProfile() {
        Profile profile = FirebaseLocalConfig.class.getAnnotation(Profile.class);
        assertThat(profile).isNotNull();
        assertThat(profile.value()).containsExactly("local");
    }

    @Test
    void firebaseConfig_isAnnotatedWithProdProfile() {
        Profile profile = FirebaseConfig.class.getAnnotation(Profile.class);
        assertThat(profile).isNotNull();
        assertThat(profile.value()).containsExactly("prod");
    }
}
