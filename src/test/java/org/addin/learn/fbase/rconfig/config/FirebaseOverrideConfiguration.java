package org.addin.learn.fbase.rconfig.config;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class FirebaseOverrideConfiguration {

    @Bean
    @Primary
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FirebaseRemoteConfig firebaseRemoteConfig() {
        return Mockito.mock(FirebaseRemoteConfig.class);
    }
}