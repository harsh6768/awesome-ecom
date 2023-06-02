package com.ecom.nabula;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EcomConfiguration extends Configuration {

    // Enabling validation
    @Valid
    @NotNull
    private ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();

    @Valid
    @NotNull
    @JsonProperty("databaseResource")
    private DataSourceFactory database = new DataSourceFactory();

}
