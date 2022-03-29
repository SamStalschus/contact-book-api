package com.contactbookapi.dto;

import lombok.*;

/** Entidade de DTO de mensagem de erro padronizada.
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessageDTO {

    private Integer statusCode;

    private String message;
}
