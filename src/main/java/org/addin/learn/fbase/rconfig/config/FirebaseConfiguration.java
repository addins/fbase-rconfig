package org.addin.learn.fbase.rconfig.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

@Configuration
public class FirebaseConfiguration {

    @Value("${application.name}")
    private String appName;

    public FirebaseApp firebaseApp() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .build();
            return FirebaseApp.initializeApp(options, appName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public String getFirebaseAppName() {
        return appName;
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FirebaseRemoteConfig firebaseRemoteConfig() {
        return FirebaseRemoteConfig.getInstance(firebaseApp());
    }
}