package org.addin.learn.fbase.rconfig.service;

import com.google.firebase.remoteconfig.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
public class FeatureToggleService {

    private final String firebaseAppName;
    private final FirebaseRemoteConfig remoteConfig;

    public void setBoolParam(String paramName, boolean value) {
        try {
            String key = formRemoteConfigParamName(paramName, firebaseAppName);
            Template template = remoteConfig.getTemplate();
            Parameter parameter = template.getParameters().get(key);
            if (parameter == null) {
                parameter = new Parameter()
                        .setValueType(ParameterValueType.BOOLEAN)
                        .setDefaultValue(ParameterValue.of(valueOf(value)))
                        .setDescription(paramName + " of " + firebaseAppName);
                template.getParameters().put(key, parameter);
            } else {
                parameter.setDefaultValue(ParameterValue.of(valueOf(value)));
            }
            remoteConfig.publishTemplate(template);
        } catch (FirebaseRemoteConfigException e) {
            throw new RuntimeException(e);
        }
    }

    private static String formRemoteConfigParamName(String paramName, String appName) {
        return appName + "__" + paramName;
    }

    public boolean getBoolParam(String paramName) {
        try {
            String key = formRemoteConfigParamName(paramName, firebaseAppName);
            Parameter parameter = remoteConfig.getTemplate().getParameters().get(key);
            return Optional.ofNullable(parameter)
                    .map(p -> (ParameterValue.Explicit)p.getDefaultValue())
                    .map(ParameterValue.Explicit::getValue)
                    .map(Boolean::valueOf)
                    .orElse(false);
        } catch (FirebaseRemoteConfigException e) {
            throw new RuntimeException(e);
        }
    }
}
