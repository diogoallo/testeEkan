package br.com.ekan.planosaude.vo.v1;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@JsonPropertyOrder({ "id", "beneficiarioId", "tipoDocumento", "dataInclusao", "dataAtualizacao" })
public class DocumentoVO  implements Serializable{

    @JsonProperty("id")
    @Mapping("id")
    private Long key;

    private String tipoDocumento;

    private String descricao;

    private String dataInclusao;

    private Long beneficiarioId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dataAtualizacao;

}
