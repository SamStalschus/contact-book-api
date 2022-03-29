package com.contactbookapi.dto;

import lombok.*;

import java.util.Map;

/** Entidade de DTO de Mensagem de parâmetros inválidos para serem usados a partir do @valid.
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvalidParamsDTO {

    private Integer statusCode;

    private String message;

    private Map<?, ?> arguments;
}