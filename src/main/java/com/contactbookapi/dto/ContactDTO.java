package com.contactbookapi.dto;

import com.contactbookapi.domain.Contact;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/** Entidade de DTO de Mensagem de contato.
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDTO {
    private Long id;

    @NotNull(message = "O nome do contato não pode estar vazio")
    @Size(max = 40, message = "O nome pode ter no máximo 40 caracteres")
    private String name;

    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Email com formato inválido")
    @Size(max = 155, message = "O email pode ter no máximo 155 caracteres")
    private String email;

    @NotNull(message = "O número de telefone não pode estar vazio")
    @Pattern(regexp = "^\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}$", message = "Numero de telefone com formato inválido")
    private String number;

    @Size(max = 40, message = "O apelido pode ter no máximo 40 caracteres")
    private String nickname;

    public static ContactDTO convert(Contact address) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(address, ContactDTO.class);
    }

    public static Contact convert(ContactDTO addressDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(addressDTO, Contact.class);
    }
}
