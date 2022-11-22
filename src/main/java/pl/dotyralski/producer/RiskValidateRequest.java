package pl.dotyralski.producer;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
class RiskValidateRequest {
    @NotNull
    Integer amount;
    @NotEmpty
    String company;
}
