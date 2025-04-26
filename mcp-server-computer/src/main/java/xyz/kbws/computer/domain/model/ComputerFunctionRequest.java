package xyz.kbws.computer.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComputerFunctionRequest {
    @JsonProperty(required = true, value = "computer")
    @JsonPropertyDescription("电脑名称")
    private String computer;

}
