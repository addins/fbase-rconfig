package org.addin.learn.fbase.rconfig.service;

import com.google.firebase.remoteconfig.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.type;
import static org.mockito.Mockito.*;

class FeatureToggleServiceTest {

    private static final String SAMPLE_APP_NAME = "app1";
    FeatureToggleService featureToggleService;

    @Mock
    FirebaseRemoteConfig firebaseRemoteConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        featureToggleService = new FeatureToggleService(SAMPLE_APP_NAME, firebaseRemoteConfig);
    }

    @Test
    void setBoolParamWithPreExistingParam() throws FirebaseRemoteConfigException {
        // preparation
        Template sampleTemplate = getSampleTemplate();
        ArgumentCaptor<Template> templateCaptor = ArgumentCaptor.forClass(Template.class);
        when(firebaseRemoteConfig.getTemplate())
                .thenReturn(sampleTemplate);

        // test
        featureToggleService.setBoolParam("param1", true);

        // assertions
        verify(firebaseRemoteConfig, times(1))
                .publishTemplate(templateCaptor.capture());
        Parameter parameter = templateCaptor.getValue().getParameters().get(SAMPLE_APP_NAME + "__" + "param1");
        assertThat(parameter)
                .isNotNull()
                .extracting(Parameter::getDefaultValue, as(type(ParameterValue.Explicit.class)))
                .extracting(ParameterValue.Explicit::getValue)
                .isEqualTo("true");
    }

    @Test
    void setBoolParamWithNoPreExistingParam() throws FirebaseRemoteConfigException {
        // preparation
        Template sampleTemplate = getSampleTemplate();
        ArgumentCaptor<Template> templateCaptor = ArgumentCaptor.forClass(Template.class);
        when(firebaseRemoteConfig.getTemplate())
                .thenReturn(sampleTemplate);

        // test
        featureToggleService.setBoolParam("new_param_1", true);

        // assertions
        verify(firebaseRemoteConfig, times(1))
                .publishTemplate(templateCaptor.capture());
        Parameter parameter = templateCaptor.getValue().getParameters().get(SAMPLE_APP_NAME + "__" + "new_param_1");
        assertThat(parameter)
                .isNotNull()
                .extracting(Parameter::getDefaultValue, as(type(ParameterValue.Explicit.class)))
                .extracting(ParameterValue.Explicit::getValue)
                .isEqualTo("true");
    }

    @Test
    void getBoolParam() throws FirebaseRemoteConfigException {
        // preparation
        Template sampleTemplate = getSampleTemplate();
        when(firebaseRemoteConfig.getTemplate())
                .thenReturn(sampleTemplate);

        // test
        boolean param1 = featureToggleService.getBoolParam("param1");

        // assertions
        assertThat(param1).isFalse();
    }

    @Test
    void getNonExistingBoolParam() throws FirebaseRemoteConfigException {
        // preparation
        Template sampleTemplate = getSampleTemplate();
        when(firebaseRemoteConfig.getTemplate())
                .thenReturn(sampleTemplate);

        // test
        boolean nonExistingParam = featureToggleService.getBoolParam("non_existing_param");

        // assertions
        assertThat(nonExistingParam).isFalse();
    }

    private static Template getSampleTemplate() throws FirebaseRemoteConfigException {
        return Template.fromJSON(
                """
                        {
                          "parameters": {
                            "app1__param1": {
                              "defaultValue": {
                                "value": "false"
                              },
                              "description": "param1 of app1",
                              "valueType": "BOOLEAN"
                            }
                          }
                        }"""
        );
    }
}