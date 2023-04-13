package com.ecom.nabula;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EcomConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("databaseResource")
    private DataSourceFactory database = new DataSourceFactory();

}
