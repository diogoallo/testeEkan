package br.com.ekan.planosaude.vo.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@JsonPropertyOrder({"id", "nome", "telefone", "dataNascimento", "dataInclusao", "dataAtualizacao"})
public class BeneficiarioVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long key;

    private String nome;

    private String cpf;

    private String telefone;

    private String dataNascimento;

    private String dataInclusao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dataAtualizacao;

}
